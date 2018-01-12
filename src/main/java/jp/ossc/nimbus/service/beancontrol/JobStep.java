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
package jp.ossc.nimbus.service.beancontrol;
// �C���|�[�g
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import org.w3c.dom.*;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.beancontrol.interfaces.*;
import jp.ossc.nimbus.service.beancontrol.resource.*;
import jp.ossc.nimbus.service.journal.*;
import jp.ossc.nimbus.service.log.*;
/**
 *	1Step�����Ǘ�����B
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class JobStep {
	//## �N���X�����o�[�ϐ��錾 ##
	/**	JOB�X�e�b�v��					*/	
	private String	mStepName = null;
	/**	�N���X��						*/	
	private String	mClassName = null;
	/**	���\�b�h��						*/	
	private String mMethodName = null;
	/**	�Q�b�^�[���\�b�h��				*/	
	private String mGetterMethodName = null;
	/**	���\�b�h�p�����[�^�Ǘ�Array		*/	
	private ArrayList	mStepParamInfoAry = null;
	/**	���t���N�V�����I�u�u�W�F�N�g	*/	
	private Object mBeanObj = null;
	/**	���t���N�V�������\�b�h			*/	
	private Method	mBlMethod = null;
	/**	���t���N�V����Getter���\�b�h	*/	
	private Method	mGetterMethod = null;
	/**	�t�@�N�g���[�R�[���o�b�N		*/	
	private BeanFlowInvokerFactoryCallBack	mFactoryCallBack = null;	
	/**	BL�N���X�I�u�W�F�N�g			*/	
	private Class	mBeanClass = null;
	private int mIncetanceType = 0;
	private String mIncetanceStepName = null;
	private BeanFlowInvokerAccessImpl invoker;
	//## �����o�[�萔�錾 	##
	/**	tag����	*/
	private	static final String C_STEP = "step" ;
	private	static final String C_STEP_NAME = "name" ;
	private	static final String C_CLASS_NAME = "className" ;
	private	static final String C_METHOD_NAME = "methodName" ;
	private	static final String C_GETTER_NAME = "getterName" ;
	private	static final String C_INSTANCE_TYPE = "type" ;
	private	static final String C_ATTRIBUTE_NAME = "attribute" ;
	private	static final String C_NOP = "" ;
	private	static final String C_OWN = "this" ;
	/** �C���X�^���X�^�C�v */
	private	static final int C_INSTACE_TYPE_NEW=0 ;
	private	static final int C_INSTACE_TYPE_INPUT=1 ;
	private	static final int C_INSTACE_TYPE_STEP=2 ;	
	private	static final String C_TYPE_NAME_NEW = "new" ;
	private	static final String C_TYPE_NAME_INPUT = "input" ;
	private	static final String C_TYPE_NAME_STEP = "step." ;
	/** JOURNAL���� */
	private	static final String C_END_STATUS = "endStatus" ;
	private	static final String C_RESULT = "result" ;
	private	static final String C_NORMAL_END = "normal end" ;
	private	static final String C_ABNORMAL_END = "abnormal end" ;
	private	static final String C_RET_NONE = "return none" ;
	
	//
	/**
	 *	�R���X�g���N�^<br>
	 */
	public JobStep(BeanFlowInvokerAccessImpl impl){
	    invoker = impl;
		this.mStepParamInfoAry = new ArrayList();
	}
	/**
	 * Method getBlObject.
	 * @return Object
	 */
	public Object getBlObject(){
		return mBeanObj ;
	}
	
	/**
	 * �X�e�b�v�\���v�f��XML���璊�o����
	 * @param element	XML�v�f
	 * @param callBack	�t�@�N�g���[�R�[���o�b�N�I�u�W�F�N�g
	 * @throws InvalidConfigurationException
	 */
	public void fillElement(Element element,
							 BeanFlowInvokerFactoryCallBack callBack,
							 List jobSteps) 
		throws InvalidConfigurationException{
		//�R�[���o�b�N�ݒ�
		mFactoryCallBack = callBack ; 
		//�X�e�b�v���ݒ�
		String name = element.getAttribute(C_STEP_NAME) ;	
		if(name == null || C_NOP.endsWith(name)){
			throw new InvalidConfigurationException("stepname none") ;
		}
		this.setStepName(name) ;
		//�N���X���ݒ�
		String className = element.getAttribute(C_CLASS_NAME) ;
		if(className == null || C_NOP.endsWith(className)){
			throw new InvalidConfigurationException("classname none") ;
		}
		this.setClassName(className) ;
		try {
			mBeanClass = Class.forName(
				mClassName,
				true,
				NimbusClassLoader.getInstance()
			);
		} catch (ClassNotFoundException e) {
			throw new InvalidConfigurationException("classname invalid" + this.mClassName ,e) ;
		}
		//���\�b�h���ݒ�
		String methodName = element.getAttribute(C_METHOD_NAME) ;
		if(methodName == null || C_NOP.endsWith(methodName)){
			throw new InvalidConfigurationException("methodname none") ;
		}
		this.setMethodName(methodName) ;
		try {
			//���t���N�V�������\�b�h�擾
			mBlMethod = mBeanClass.getMethod(this.mMethodName,(Class[])null);
		} catch (SecurityException e1) {
			throw new InvalidConfigurationException("methodname invalid " +className + "#" + this.mMethodName ,e1) ;
		} catch (NoSuchMethodException e1) {
			throw new InvalidConfigurationException("methodname invalid"  +className + "#" + this.mMethodName ,e1) ;
		}
		String tmp= element.getAttribute(C_INSTANCE_TYPE) ;
		if(tmp == null || C_NOP.endsWith(tmp)){
			this.mIncetanceType = C_INSTACE_TYPE_NEW ;
		}else if(tmp.equals(C_TYPE_NAME_NEW)){
			this.mIncetanceType = C_INSTACE_TYPE_NEW ;
		}else if(tmp.equals(C_TYPE_NAME_INPUT)){
			this.mIncetanceType = C_INSTACE_TYPE_INPUT ;
		}else if(tmp.startsWith(C_TYPE_NAME_STEP)){
			this.mIncetanceType = C_INSTACE_TYPE_STEP ;
			this.mIncetanceStepName = tmp.substring(C_TYPE_NAME_STEP.length()) ;
		}else{
			throw new InvalidConfigurationException(C_INSTANCE_TYPE + "is invalid value is " + tmp ) ;
		}
		String outputGetterName = element.getAttribute(C_GETTER_NAME) ;			
		if(outputGetterName != null && outputGetterName.length()>0){
			this.mGetterMethodName = outputGetterName ;
		}
		Logger logger = mFactoryCallBack.getLogger();
		if(logger != null){
			String[] ary = new String[2];
			ary[0] = this.mGetterMethodName == null ? "null" : this.mGetterMethodName;
			ary[1] = className;
			logger.write("BEANF00001",ary);
		}
		if(mGetterMethodName != null && 
			!C_NOP.endsWith(mGetterMethodName) &&
			!C_OWN.equals(mGetterMethodName)){
			//�Q�b�^�[���̂�"this"�ł͂Ȃ��ꍇ
			try {
				mGetterMethod = mBeanClass.getMethod(this.mGetterMethodName,(Class[])null);
				if(logger != null){
					String[] ary = new String[2];
					ary[0] = this.mGetterMethodName;
					ary[1] = className;
					logger.write("BEANF00002",ary);
				}		
			} catch (SecurityException e2) {
				if(logger != null){
					String[] ary = new String[2];
					ary[0] = this.mGetterMethodName;
					ary[1] = className;
					logger.write("BEANF00003",ary,e2);
				}
				throw new InvalidConfigurationException("gettermethodname invalid " + mGetterMethodName,e2) ;
			} catch (NoSuchMethodException e2) {
				if(logger != null){
					String[] ary = new String[2];
					ary[0] = this.mGetterMethodName;
					ary[1] = className;					
					logger.write("BEANF00004",ary,e2);
				}
				throw new InvalidConfigurationException("gettermethodname invalid " + mGetterMethodName,e2) ;
			}
		}
		NodeList attList = element.getElementsByTagName(C_ATTRIBUTE_NAME) ;
		for(int cnt= 0 ;cnt<attList.getLength();cnt++ ){
			StepParamInformation param = new StepParamInformation(invoker) ;
			param.fillParameter((Element)attList.item(cnt),this.mBeanClass,callBack,jobSteps) ;
			mStepParamInfoAry.add(param) ;
		}
	}
	/**
	 *	JOB�X�e�b�v�����s����B<br>
	 */
	public Object invokeStep(Object input,
							HashMap execBlHash,
							ResourceManager rm,
							BeanFlowMonitor monitor) throws InvocationTargetException, InterruptedException{
		Object ret = null ;
		Object blObj = null ;
		Journal jnl = this.invoker.getJournal() ;
		try{
            ((BeanFlowMonitorImpl)monitor).setCurrentStepName(mStepName);
            ((BeanFlowMonitorImpl)monitor).checkStop();
            ((BeanFlowMonitorImpl)monitor).checkSuspend();
			if(jnl!=null){
				jnl.addStartStep(C_STEP, mFactoryCallBack.getEditorFinder()) ;
				jnl.addInfo(C_STEP_NAME,this.mStepName);
				jnl.addInfo(C_CLASS_NAME,this.mClassName) ;
				jnl.addInfo(C_METHOD_NAME,this.mMethodName) ;
			}
			switch(this.mIncetanceType){
			case C_INSTACE_TYPE_NEW:
				try{
					blObj = mBeanClass.newInstance();
				}catch(InstantiationException iex){
					// BL�I���L�^
					throw new InvalidConfigurationException("NOT_CLASS_INSTANCE",iex);
				}catch(IllegalAccessException ilex){
					// BL�I���L�^
					throw new InvalidConfigurationException("NOT_ILLEGAL_ACCESS",ilex);
				}
				break ;
			case C_INSTACE_TYPE_INPUT :
				blObj = input ;
				break ;
			default :
				blObj = execBlHash.get(this.mIncetanceStepName) ;
				break ;
			}

			execBlHash.put(this.getStepName(),blObj) ;
			// ���s�a�k ���\�b�h�C���{�[�N
			try{
				// ���s�a�k�Z�b�^����
				this.setParam(blObj,execBlHash,rm,input);
				this.mBlMethod.invoke(blObj,(Object[])null);
			}catch(IllegalAccessException iex){
				if(jnl!=null){
					jnl.addInfo(C_END_STATUS,C_ABNORMAL_END) ;
					jnl.addInfo(C_RESULT,iex) ;
				}
				throw new InvalidConfigurationException("ILLEGAL_ACCESS",iex);
			} catch (InvocationTargetException e) {
				if(jnl!=null){
					jnl.addInfo(C_END_STATUS,C_ABNORMAL_END) ;
					jnl.addInfo(C_RESULT,e.getTargetException()) ;
				}
				throw e;
			} catch (NoSuchMethodException e) {
				if(jnl!=null){
					jnl.addInfo(C_END_STATUS,C_ABNORMAL_END) ;
					jnl.addInfo(C_RESULT,e) ;
				}
				throw new InvalidConfigurationException("NOSUCHMETHOD",e);
			}
			if(mGetterMethodName != null){
				if(C_OWN.equals(mGetterMethodName)){
					ret = blObj ;
				}else{
					try{
						//mGetterMethodName��null�łȂ��A���A"This"�ł�
						//�Ȃ��Ȃ�΁AmGetterMethod�͗L���Ȓl�������Ă����
						//���f���Ă悢�B�����łȂ���΁AfillElement�ŗ�O
						//����������B
						ret = mGetterMethod.invoke(blObj,(Object[])null) ;
					}catch(IllegalAccessException iex){
						if(jnl!=null){
							jnl.addInfo(C_END_STATUS,C_ABNORMAL_END) ;
							jnl.addInfo(C_RESULT,iex) ;
						}
						throw new InvalidConfigurationException("NOT_ILLEGAL_ACCESS",iex);
					} catch (InvocationTargetException e) {
						if(jnl!=null){
							jnl.addInfo(C_END_STATUS,C_ABNORMAL_END) ;
							jnl.addInfo(C_RESULT,e.getTargetException()) ;
						}
						throw e;
					}
				}
				if(jnl!=null){
					jnl.addInfo(C_END_STATUS,C_NORMAL_END) ;
					jnl.addInfo(C_RESULT,ret) ;
				}
			}else{
				if(jnl!=null){
					jnl.addInfo(C_RESULT,C_RET_NONE) ;
				}
			}
		}finally{
			if(jnl!=null){
				jnl.addEndStep();
			}
		}
		return ret ;
	}
	/**
	 *	�O��a�k�̃Q�b�^���\�b�h�������͕����������a�k�̃Z�b�^�Ɋi�[����B<br>
	 * @throws JclException
	 * @throws BLException
	 */
	protected void setParam(Object blInstance,
						HashMap execBlHash,
						ResourceManager rm,
						Object inputObj) 
		throws NoSuchMethodException, 
				InvocationTargetException, 
				IllegalAccessException{
		// �SPARAM�����ɑ΂��ăZ�b�^���\�b�h���s
		for(int i =0, max = mStepParamInfoAry.size(); i < max; i++){
			StepParamInformation jclParamObj = (StepParamInformation)mStepParamInfoAry.get(i);
				jclParamObj.invokeParameter(blInstance,
											execBlHash,
											rm,
											inputObj);
		}
	}
	/**
	 *	JOB�X�e�b�v����ݒ肷��B<br>
	 *	@param stepName	�X�e�b�v��
	 */
	public void setStepName(String stepName){
		this.mStepName = stepName;
	}
	/**
	 *	�N���X����ݒ肷��B<br>
	 *	@param className	�N���X��
	 */
	public void setClassName(String className){
		this.mClassName = className;
	}
	/**
	 *	���\�b�h����ݒ肷��B<br>
	 *	@param methodName	�N���X��
	 */
	public void setMethodName(String methodName){
		this.mMethodName = methodName;
	}
	/**
	 *	JOB�X�e�b�v�����擾����B<br>
	 *	@return String	�X�e�b�v��
	 */
	public String getStepName(){
		return this.mStepName;
	}
	/**
	 *	�N���X�����擾����B<br>
	 *	@return String	�N���X��
	 */
	public String getClassName(){
		return this.mClassName;
	}
	/**
	 *	���\�b�h�����擾����B<br>
	 *	@return	���\�b�h��
	 */
	public String getMethodName(){
		return this.mMethodName;
	}
	/**
	 * ���s����Bean�̃N���X���o�͂���B
	 * @return Class
	 */
	public Class getBeanClass(){
		return this.mBeanClass ;
	}
	//
}
