/*
 * This software is distributed under following license based on modified BSD
 * style license.
 * ----------------------------------------------------------------------
 * 
 * Copyright 2003 The Nimbus Project. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE NIMBUS PROJECT ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 * NO EVENT SHALL THE NIMBUS PROJECT OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of the Nimbus Project.
 */
// �p�b�P�[�W
package jp.ossc.nimbus.service.scheduler;
//�C���|�[�g
import jp.ossc.nimbus.core.*; 
import jp.ossc.nimbus.lang.*; 
import jp.ossc.nimbus.daemon.*;
import jp.ossc.nimbus.ioc.*;
import jp.ossc.nimbus.service.ioccall.*;
import jp.ossc.nimbus.service.log.*;
import java.util.*;
import jp.ossc.nimbus.service.sequence.*;
/** 
 * �^�C���X�P�W���[���[�N���X<p>
 * ��莞�Ԗ��ɖ₢���킹���グ�Ďw�肳�ꂽ�R�}���h��Queue�ɓ����
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class QueueEntrySchdulerService
	extends ServiceBase
	implements QueueEntrySchdulerServiceMBean, 
				DaemonRunnable {
	
    private static final long serialVersionUID = -4014975977474880347L;
    
    private static final String C_REQUEST_ID = "REQUEST_ID";
	private static final String C_USER_ID = "USER_ID";
	/** �X�P�W���[���f�[���� */	
	private Daemon mSchedulerDaemon = null ;
	/** EJB�t�@�T�[�h�R�[���[�T�[�r�X�� */	
	private ServiceName mFacadeCallServiceName = null ;
	/** EJB�t�@�T�[�h�R�[���[ */	
	private FacadeCaller mFacadeCaller = null ;
	/** �^�C�}�[����			*/	
	private Date mTimerSecounds = new Date();
	/** �V�X�e������			*/	
	private Date mSystemSecounds = null ;
	/**	�^�C�}�C���^�[�o��		*/	
	private long mInterval = 60000;
	/**	���K�[�T�[�r�X�� */	
	private ServiceName mLoggerServiceName = null;
	/**	�^�C�}�C���^�[�o��		*/	
	private Logger mLogger = null;
	/**	�^�C�}�C���^�[�o��		*/	
	private String mFlowKey = null;	
	/**	�㏈�����s��			*/	
	private static final String C_CONSUME_OK = "1";
	/**	�㏈�����s�s��			*/	
	private static final String C_CONSUME_NG = "0"; 
	private ServiceName mSequenceServiceName = null;
	private Sequence mSequence = null ;
	private String mUserId = null ;
	/**
	 * �R���X�g���N�^�[
	 */
	public QueueEntrySchdulerService() {
		super();
	}
	/**
	 * �R���X�g���N�^�[
	 */
	public QueueEntrySchdulerService(ServiceBaseSupport support) {
		super(support);
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.core.ServiceBaseSupport#startService()
	 */
	public void startService(){
        if(mFacadeCallServiceName != null) {
            mFacadeCaller = (FacadeCaller)ServiceManagerFactory.getServiceObject(mFacadeCallServiceName) ;
        }
		if(mFlowKey == null || mFlowKey.length() == 0){
			throw new ServiceException("QueueEntrySchdulerService","FlowKey is Empty"); 
		}
		if(mLoggerServiceName != null){
			mLogger = (Logger)ServiceManagerFactory.getServiceObject(mLoggerServiceName) ;
		}
		if(mSequenceServiceName != null){
			mSequence = (Sequence)ServiceManagerFactory.getServiceObject(mSequenceServiceName) ;
		}
		mSchedulerDaemon = new Daemon(this) ;
		mSchedulerDaemon.setDaemon(true) ;
		mSchedulerDaemon.start() ;		
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.core.ServiceBaseSupport#stopService()
	 */
	public void stopService(){
		mSchedulerDaemon.stop() ;
		mSchedulerDaemon = null ;
		this.mFacadeCaller = null ;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#setFacadeServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public void setFacadeServiceName(ServiceName name) {
		this.mFacadeCallServiceName = name ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#getFacadeServiceName()
	 */
	public ServiceName getFacadeServiceName() {
		return this.mFacadeCallServiceName;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#setInterval(long)
	 */
	public void setInterval(long msecs) {
		this.mInterval = msecs ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#getInterval()
	 */
	public long getInterval() {
		return this.mInterval;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#setGetTaskFlowKey(java.lang.String)
	 */
	public void setGetTaskFlowKey(String name) {
		this.mFlowKey = name ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#setGetTaskFlowKey()
	 */
	public String getGetTaskFlowKey() {
		return this.mFlowKey ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#setLogServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public void setLogServiceName(ServiceName name) {
		this.mLoggerServiceName = name ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.daemon.DaemonRunnable#onStop()
	 */
	public boolean onStop() {
		return true;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.daemon.DaemonRunnable#onSuspend()
	 */
	public boolean onSuspend() {
		return true;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.daemon.DaemonRunnable#onResume()
	 */
	public boolean onResume() {
		return true;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.daemon.DaemonRunnable#provide(jp.ossc.nimbus.daemon.DaemonControl)
	 */
	public Object provide(DaemonControl ctrl) throws Exception {
		String procFlg = null ;
		//�V�X�e���������X�V����B
		this.mSystemSecounds = new java.util.Date();
		if(dateOver(mInterval)){
			procFlg = C_CONSUME_NG;
			try {
				if(mLogger != null){
					mLogger.write("SCH0000200202",String.valueOf(mInterval));
				}
				Thread.sleep(mInterval);
			} catch (InterruptedException e) {
				return null;
			}
		}else{
			procFlg = C_CONSUME_OK;
			if(mLogger != null){
				mLogger.write("SCH0000200202",String.valueOf(mInterval));
			}
		}
		return procFlg ;
	}
	//
	/**
	 * �w��C���^�[�o���P�ʂŔ�r����<BR>
	 * @param long interval
	 * @return true �̂Ƃ��w��C���^�[�o���Ԋu�Ŕ�r���P����r���Q
	 */
	private boolean dateOver(long interval){
		boolean ret = false;
		long lngdt1 = this.mTimerSecounds.getTime() / interval;
		long lngdt2 = this.mSystemSecounds.getTime() / interval;
		if(lngdt1 > lngdt2){
			ret = true;
		}else{
			ret = false;
		}
		return ret;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.daemon.DaemonRunnable#consume(java.lang.Object, jp.ossc.nimbus.daemon.DaemonControl)
	 */
	public void consume(Object paramObj, DaemonControl ctrl) throws Exception {
		if(paramObj == null){
			return;
		}
		if(mLogger!= null){
			mLogger.write("SCH0000200301");
		}
		String procFlg = (String)paramObj ;
		if(procFlg.equals(C_CONSUME_OK)){
			try {
				if(mLogger!= null){
					mLogger.write("SCH0000200302");
				}
				// Exception���ɂ����ɏ������Ȃ��悤�ɐ�Ɏ��Ԃ����Z���Ă����܂�
				FacadeValue val = FacadeValueAccess.createCommandsValue() ; 
				Command cmd = FacadeValueAccess.createCommand(this.mFlowKey,this.mTimerSecounds) ;
				val.addCommand(cmd) ;
				if(this.mUserId != null){
					val.putHeader(C_USER_ID,mUserId) ;
				}
				if(this.mSequence != null){
					val.putHeader(C_REQUEST_ID,mSequence.increment()) ;
				}
				FacadeValue ret = null ;
				this.mTimerSecounds = new java.util.Date(this.mTimerSecounds.getTime() + mInterval);
				ret = this.mFacadeCaller.syncFacadeCall(val) ;
				// �N�����Ԃ̃X�P�W���[���f�[�^�𑗐M���܂�
				Command retcmd = (Command)ret.getCommand(0) ;
				List ary = (List)retcmd.getOutputObject() ;				
				val = FacadeValueAccess.createCommandsValue() ;
				if(this.mUserId != null){
					val.putHeader(C_USER_ID,mUserId) ;
				}
				if(this.mSequence != null){
					val.putHeader(C_REQUEST_ID,mSequence.increment()) ;
				}
				Iterator ite = ary.iterator() ;
				while(ite.hasNext()){
					Command info = (Command)ite.next() ;
					UnitOfWork uw = FacadeValueAccess.createUnitOfWork() ;
					uw.addCommand(info) ;
					val.addUnitOfWork(uw) ;
					if(mLogger!= null){
						mLogger.write("SCH0000200303",info.getFlowKey());
					}
				}
				if(val.size()>0){
					this.mFacadeCaller.unsyncFacadeCall(val) ;
					if(mLogger!= null){
						mLogger.write("SCH0000200304");
					}
				}else{
					if(mLogger!= null){
						mLogger.write("SCH0000200305");
					}
				}
			} catch (Exception e) {
				mLogger.write("SCH0000200306",e);
				throw e ;
			}
		}
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.daemon.DaemonRunnable#garbage()
	 */
	public void garbage() {
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.daemon.DaemonRunnable#onStart()
	 */
	public boolean onStart() {
		return true;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#getSequenceServiceName()
	 */
	public ServiceName getSequenceServiceName() {
		return mSequenceServiceName;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#getUserId()
	 */
	public String getUserId() {
		return mUserId;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#setSequenceServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public void setSequenceServiceName(ServiceName name) {
		mSequenceServiceName = name;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.scheduler.QueueEntrySchdulerServiceMBean#setUserId(java.lang.String)
	 */
	public void setUserId(String string) {
		mUserId = string;
	}
    public void setFacadeCaller(FacadeCaller facadeCaller) {
        mFacadeCaller = facadeCaller;
    }
    public void setLogger(Logger logger) {
        mLogger = logger;
    }
    public void setSequence(Sequence sequence) {
        mSequence = sequence;
    }

}
