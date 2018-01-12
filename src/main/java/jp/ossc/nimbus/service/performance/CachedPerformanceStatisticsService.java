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
package jp.ossc.nimbus.service.performance;
// �C���|�[�g
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.ListIterator;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.lang.ServiceException;
import jp.ossc.nimbus.util.CsvArrayList;
//
/**
 *	�X�^�e�B�X�e�B�N�X�Ǘ��N���X�B<BR>
 *	�X�^�e�B�X�e�B�N�X�̌����A�o�^���s���B<BR>
 *	@author 	NRI Hirotaka.Nakano
 *				�X�V�F
 */
public class CachedPerformanceStatisticsService extends ServiceBase 
										   implements PerformanceStatistics,
										   				CachedPerformanceStatisticsServiceMBean{
	
    private static final long serialVersionUID = -3405299217501638457L;
    
    //##	�����o�[�ϐ��錾	##
	protected Hashtable mHash = null ;
	protected String mClassName = null ;
	protected Class mClsRec = null ;
	//
	//
	/**
	 * �R���X�g���N�^�B<BR>
	 * Hash���C���X�^���V���O����Key���Z�b�g����B<BR>
	 */
	public CachedPerformanceStatisticsService () {
		super() ;
	}
	public void createService(){
		mHash = new Hashtable(1024,256) ;
	}
	public void stopService(){
		mHash.clear() ;
	}		
	public void destroyService(){
		mHash = null ;
		mClassName = null ;
		mClsRec = null ;
	}
	//
	/**
	 *	�p�t�H�[�}���X�G���g�����\�b�h<BR>
	 *	�p�t�H�[�}���X�I�u�W�F�N�g�̃G���g�����s���B<BR>
	 * @param key	�X�^�e�B�X�e�B�N�X��
	 * @param msec	�p�t�H�[�}���X����
	 */
	public void entry (String key,long msec){
		synchronized(this) {
			PerformanceRecordOperator performanceObj = null ;
			performanceObj = (PerformanceRecordOperator)mHash.get(key);
			if(performanceObj != null) {
				performanceObj.entry(msec);
			} else {
				try {
					performanceObj = (PerformanceRecordOperator)mClsRec.newInstance();
				} catch (InstantiationException e) {
					throw new ServiceException("PEFORMANCE001","InstantiationException",e) ;	
				} catch (IllegalAccessException e) {
					throw new ServiceException("PEFORMANCE001","IllegalAccessException",e) ;	
				}
				performanceObj.setResourceId(key) ;
				performanceObj.entry(msec);
				mHash.put(key,performanceObj);
			}
		}
	}
	//
	/**
	 *	�p�t�H�[�}���XHASH���N���A����B
	 */
	public void clear() {
		synchronized(this) {
			this.mHash.clear();
		}
	}
	//
	/**
	 *	�����o�̓��\�b�h<BR>
	 *	�w��̃\�[�g�L�[�Ń\�[�g���s���B<BR>
	 * @param sortKey	�\�[�g�L�[
	 * @param isUpset		�����A�~���̎w��
	 * @return String[] �\�[�g����
	 */
	public String[] toStringAry (int sortKey,boolean isUpset){
		// ���O���擾����
		ArrayList sortList = new ArrayList();
		CsvArrayList retAry = new CsvArrayList() ;
		Hashtable tb = null ;
		synchronized(this) {
			tb = (Hashtable)mHash.clone();
		}
		/** �f�[�^���X�g����ꍀ�ڂÂ��o���B*/
		for (Enumeration enumeration = tb.elements() ; enumeration.hasMoreElements() ;) {
			PerformanceRecord item = (PerformanceRecord)enumeration.nextElement() ;
			//�L�[�\�[�g���\�b�h���R�[��
			_sortList(sortList,item,sortKey,isUpset);
		}
		/** �L�[�\�[�g���X�g����o�͕����z��Ƀf�[�^��]�L */
		for (ListIterator iterator = sortList.listIterator() ; iterator.hasNext() ;) {
			//KEY�����f�[�^�擾�ECSV����
			String sortItem = (String)iterator.next() ;
			CsvArrayList keyAry = new CsvArrayList() ;
			keyAry.split(sortItem,";");
			//�L�[��HASH����Ώۃp�t�H�[�}���X�}�l�[�W�������o���B
			PerformanceRecord item = (PerformanceRecord)mHash.get(keyAry.getStr(0));
			//�o�̓��X�g�Ƀp�t�H�[�}���X�����i�[
			if (item != null){
				retAry.add(item.toString());
			}else{
			}
		}
		/** �o�� */
		String[] retStrAry = retAry.toStringAry() ;
		return retStrAry ;

	}
	//
	/**
	 *	�\�[�g���\�b�h<BR>
	 *	�w��̃\�[�g�L�[�Ń\�[�g���s���B<BR>
	 * @param sortList	�\�[�g���ʊi�[�z��
	 * @param item		PerformanceManger�I�u�W�F�N�g
	 * @param sortKey	�\�[�g�L�[
	 * @param isUpset	�����A�~���̎w��
	 */
	private void _sortList (ArrayList sortList,PerformanceRecord item,int sortKey,boolean isUpset){
		// ���O���擾����
		String cmpKey = null;
		if(sortKey==C_NAME){
			cmpKey = item.getResourceId() ;
		}else if(sortKey==C_BEST){
			Long tmpLong = new Long(item.getBestPerformance()) ;
			cmpKey = tmpLong.toString() ;
		}else if(sortKey==C_WORST){
			Long tmpLong = new Long(item.getWorstPerformance()) ;
			cmpKey = tmpLong.toString() ;
		}else if(sortKey == C_AVERAGE){
			Long tmpLong = new Long(item.getAveragePerformance()) ;
			cmpKey = tmpLong.toString() ;
		}else if(sortKey == C_COUNT){
			Long tmpLong = new Long(item.getCallTime()) ;
			cmpKey = tmpLong.toString() ;
		}
		/** sort�f�[�^��������쐬����<BR>
		 *	resourceId + ";" ��r�f�[�^  */
		String rscId = item.getResourceId() + ";" + cmpKey;
//		rscId = rscId + ";" + cmpKey ;
		int entryCnt = 0 ;
		/** sortList�Ƀ\�[�g�C���T�[�g����B */
		for (ListIterator iterator = sortList.listIterator() ; iterator.hasNext() ;entryCnt++) {
			//���X�g�̃R���y�A���ڂ����o���B
			String destCmp = (String)iterator.next() ;
			CsvArrayList parse = new CsvArrayList();
			parse.split(destCmp,";");
			//�R���y�A
			int ret = cmpKey.compareTo(parse.getStr(1));
			if(isUpset){
				if(ret<=0){
					break;
				}
			}else{
				if(ret>=0){
					break;
				}
			}
		}
		sortList.add(entryCnt,rscId) ;
	}
	/**
	 *	�\�[�g���\�b�h<BR>
	 *	�w��̃\�[�g�L�[�Ń\�[�g���s���B<BR>
	 * @param sortList	�\�[�g���ʊi�[�z��
	 * @param item		PerformanceManger�I�u�W�F�N�g
	 * @param sortKey	�\�[�g�L�[
	 * @param isUpset	�����A�~���̎w��
	 */
	private void _sortListByVal (ArrayList sortList,PerformanceRecord item,int sortKey,boolean isUpset){
		// ���O���擾����
		String cmpKey = null;
		if(sortKey==C_NAME){
			cmpKey = item.getResourceId() ;
		}else if(sortKey==C_BEST){
			Long tmpLong = new Long(item.getBestPerformance()) ;
			cmpKey = tmpLong.toString() ;
		}else if(sortKey==C_WORST){
			Long tmpLong = new Long(item.getWorstPerformance()) ;
			cmpKey = tmpLong.toString() ;
		}else if(sortKey==C_AVERAGE){
			Long tmpLong = new Long(item.getAveragePerformance()) ;
			cmpKey = tmpLong.toString() ;
		}else if(sortKey==C_COUNT){
			Long tmpLong = new Long(item.getCallTime()) ;
			cmpKey = tmpLong.toString() ;
		}
		/** sort�f�[�^��������쐬����<BR>
		 *	resourceId + ";" ��r�f�[�^  */
		String rscId = item.getResourceId() + ";" + cmpKey;
		int entryCnt = 0 ;
		/** sortList�Ƀ\�[�g�C���T�[�g����B */
		for (ListIterator iterator = sortList.listIterator() ; iterator.hasNext() ;entryCnt++) {
			//���X�g�̃R���y�A���ڂ����o���B
			String destCmp = (String)iterator.next() ;
			CsvArrayList parse = new CsvArrayList();
			parse.split(destCmp,";");
			//�R���y�A
			int ret = 0;
			if (sortKey==C_NAME){
				ret = cmpKey.compareTo(parse.getStr(1));
			}else{
				long value = Long.valueOf(parse.getStr(1)).longValue();
				long cmpvalue = Long.valueOf(cmpKey).longValue();
				if (cmpvalue == value){
					ret = 0;
				}else if (cmpvalue < value){
					ret = -1;
				}else{
					ret = 1;
				}
			}
			if(isUpset){
				if(ret<=0){
					break;
				}
			}else{
				if(ret>=0){
					break;
				}
			}
		}
		sortList.add(entryCnt,rscId) ;
	}
	/**
	 *	List�f�[�^�擾<BR>
	 *	�w��̃\�[�g�L�[�Ń\�[�g���s���B<BR>
	 * @param sortKey	�\�[�g�L�[
	 * @param isUpset	�����A�~���̎w��
	 * @return ArrayList	�\�[�g���v���
	 */
	public ArrayList toAry (int sortKey,boolean isUpset){
		ArrayList sortList = new ArrayList();
		ArrayList retAry = new ArrayList();
		Hashtable tb = null ;
		synchronized(this) {
			tb = (Hashtable)mHash.clone();
		}
		/** �f�[�^���X�g����ꍀ�ڂÂ��o���B*/
		for (Enumeration enumeration = tb.elements() ; enumeration.hasMoreElements() ;) {
			PerformanceRecord item = (PerformanceRecord)enumeration.nextElement() ;
			//�L�[�\�[�g���\�b�h���R�[��
			if (sortKey==C_NAME){
				_sortList(sortList,item,sortKey,isUpset);
			}else{
				_sortListByVal(sortList,item,sortKey,isUpset);
			}
		}
		/** �L�[�\�[�g���X�g����o�͕����z��Ƀf�[�^��]�L */
		for (ListIterator iterator = sortList.listIterator() ; iterator.hasNext() ;) {
			//KEY�����f�[�^�擾�ECSV����
			String sortItem = (String)iterator.next() ;
			CsvArrayList keyAry = new CsvArrayList() ;
			keyAry.split(sortItem,";");
			//�L�[��HASH����Ώۃp�t�H�[�}���X�}�l�[�W�������o���B
			PerformanceRecord item = (PerformanceRecord)mHash.get(keyAry.getStr(0));
			if(item != null){
				retAry.add(item);
			}
		}
		return retAry ;
	}
	//
	/**
	 * Method setRecordClassName.
	 * @param className
	 */
	//
	public void setRecordClassName(String className) {
		this.mClassName = className ;
		try {
			mClsRec = Class.forName(
				className,
				true,
				NimbusClassLoader.getInstance()
			);
		} catch (ClassNotFoundException e) {
			throw new ServiceException("PEFORMANCE010","ClassNotFoundException classnamse = " + className,e) ;	
		}
	}
	/**
	 * Method getRecordClassName.
	 * @return String
	 */
	public String getRecordClassName() {
		return this.mClassName ;
	}
}
