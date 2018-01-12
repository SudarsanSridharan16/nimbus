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
import org.w3c.dom.*;
import jp.ossc.nimbus.lang.*;
import java.util.*;
import java.beans.*;
import jp.ossc.nimbus.service.beancontrol.resource.*;
import jp.ossc.nimbus.util.*;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.beans.*;
import jp.ossc.nimbus.service.resource.*;
import jp.ossc.nimbus.service.beancontrol.interfaces.*;
import jp.ossc.nimbus.service.journal.*;
//
/**
 *	Step�p�����[�^�����Ǘ����܂��B
 */
public class StepParamInformation {
	
    private static final long serialVersionUID = 2462305701703274968L;
    
    //## �N���X�����o�[�ϐ��錾 ##
	private static final String C_SETTER = "set";
	private static final String C_THIS = "this";
	/** �Z�b�^�[���@*/
	protected String mSetterName = null ;
	/** value���@*/
	protected String mValue = null ;
	/** ���[�h�@*/
	protected int mMode = 0;
	//## �����o�[�萔�錾 	##
	/** ���l���[�h�@*/
	protected static final int C_VALUE_MOD = 1 ;
	protected static final String C_VALUE_MOD_STR = "value";
	/** �T�[�r�X���[�h�@*/
	protected static final int C_SERVICE_MOD = 2 ;
	protected static final String C_SERVICE_MOD_STR = "service";
	/** �X�e�b�v���[�h */
	protected static final int C_STEP_MOD = 3 ;
	protected static final String C_STEP_MOD_STR = "step" ;
	/** ���\�[�X���[�h */
	protected static final int C_RESOURCE_MOD = 4;
	protected static final String C_RESOURCE_MOD_STR = "resource";
	/** �C���v�b�g���[�h�@*/
	protected static final int C_INPUT_MOD = 5 ;
	protected static final String C_INPUT_MOD_STR = "input";
	/** name�@���� */
	protected static String C_NAME_ATT = "name";
	/** type ���� */
	protected static String C_TYPE_ATT = "type";
	/** value ���� */
	protected static String C_VALUE_ATT = "value";
	protected static String C_VALUE_NULL = "null";
	/** �Z�b�^�[���\�b�h */
//	protected Method mSetterMethod = null;
	protected HashMap mParamHash = null ; 
	protected boolean mMapFlg = false ;
	/** �T�[�r�X��Editor */
	protected ServiceNameEditor mEditor;
	/** �Z�b�^�[�����̌^��TransanctionResource���ǂ��� */
	protected boolean mIsTransactionResourceSetter = false;
	/** BeanFlowFactory �̃R�[���o�b�N*/
	protected BeanFlowInvokerFactoryCallBack mCallBack = null;
	protected Method mStepMethod = null ;
	protected String mRefStepName = null ;
	protected BeanFlowInvokerAccessImpl invoker;
	/**
	 * Constructor for JclParamInformation.
	 */
	protected StepParamInformation(BeanFlowInvokerAccessImpl impl) {
		super();
		invoker = impl;
		mEditor = new ServiceNameEditor();
		this.mParamHash = new HashMap() ;
	}
	//
	/**
	 * XML�f�[�^������������𒊏o����
	 * @param element	DOM�̃G�������g
	 * @param clazz	�Z�b�^�[���݃N���X�I�u�W�F�N�g
	 */
	public void fillParameter(Element element,
								Class clazz, 
								BeanFlowInvokerFactoryCallBack callBack,
								List jobSteps){
		mCallBack = callBack ;
		Method mSetterMethod = null;
		//name�������擾���A�ێ�����B
		String nameAttr = getAttMustBeSpecified(element,C_NAME_ATT);
		nameAttr = C_SETTER + nameAttr;
		setSetterName(nameAttr);
		//type�������擾���A�ێ�����B
		String tmp = getAttMustBeSpecified(element,C_TYPE_ATT);
		setSetterMode(tmp);
		//value�������擾���A�ێ�����B
		String valueAttr = element.getAttribute(C_VALUE_ATT);
		if( (valueAttr == null) || (valueAttr.length() == 0) ){
			String content = MetaData.getElementContent(element);
            if(content == null){
                content = "";
            }
			if(content != null) valueAttr = content;
		}
		if( (valueAttr == null) || (valueAttr.length() == 0) ){
			int mode = getSetterMode();
			//�ȉ��̃��[�h�ł́A�K��value�������w�肳��Ă��Ȃ���΂Ȃ�Ȃ��B
			if((mode == C_SERVICE_MOD) || (mode == C_STEP_MOD) || ( mode == C_RESOURCE_MOD)){
				throw new InvalidConfigurationException(this.getClass().getName()+ " value attr must be specified.");
			}
		}
		if(getSetterMode() == C_STEP_MOD){
			CsvArrayList csvArrayList = new CsvArrayList() ;
			csvArrayList.split(valueAttr,"#");
			mRefStepName = csvArrayList.getStr(0);
			String getterStr = csvArrayList.getStr(1);
			if(!C_THIS.equals(getterStr)){
				//BL�̃C���X�^���X���擾����B
				Iterator ite = jobSteps.iterator();
				boolean findFlg = false ;
				while(ite.hasNext()){
					JobStep js = (JobStep)ite.next() ; 
					if(js.getStepName().equals(mRefStepName)){
						Class claz = js.getBeanClass() ;
						Method method = null;
						try {
							method = claz.getMethod(getterStr,(Class[])null);
						} catch (SecurityException e) {
							throw new InvalidConfigurationException("Step Getter is invalid " + valueAttr ,e) ;
						} catch (NoSuchMethodException e) {
							throw new InvalidConfigurationException("Step Getter is invalid " + valueAttr ,e) ;
						}
						findFlg = true ;
						mStepMethod = method ;
						break ;
					}
				}
				if(!findFlg){
					throw new InvalidConfigurationException("Step Getter is none " + valueAttr ) ;
				}
			}
		}
		setValue(valueAttr);
		//���\�b�h�������o�ϐ��Ɋm��
		Method[] methods = clazz.getMethods();
		for(int rCnt=0;rCnt<methods.length;rCnt++){
			//���O����v������̂�T��
			if (methods[rCnt].getName().equals(nameAttr)){
				Class[] params = methods[rCnt].getParameterTypes();
				//������1�Ȃ�΃Z�b�^�[�Ƃ��ă����o�ϐ��ɕێ�
				if (params.length == 1){
					mSetterMethod = methods[rCnt];
					if(params[0].isAssignableFrom(TransactionResource.class)){
						mIsTransactionResourceSetter = true;
					}
					else{
						mIsTransactionResourceSetter = false;
					}
					if(Byte.TYPE.equals(params[0])){
						params[0] = Byte.class;
					}else if(Character.TYPE.equals(params[0])){
						params[0] = Character.class;
					}else if(Short.TYPE.equals(params[0])){
						params[0] = Short.class;
					}else if(Integer.TYPE.equals(params[0])){
						params[0] = Integer.class;
					}else if(Long.TYPE.equals(params[0])){
						params[0] = Long.class;
					}else if(Float.TYPE.equals(params[0])){
						params[0] = Float.class;
					}else if(Double.TYPE.equals(params[0])){
						params[0] = Double.class;
					}else if(Boolean.TYPE.equals(params[0])){
						params[0] = Boolean.class;
					}
					this.mParamHash.put(params[0],mSetterMethod) ;
				}
			}
		}
		if(clazz.isAssignableFrom(Map.class)){			 			
			this.mMapFlg = true ;
		}
		//�Z�b�^�[��������Ȃ�������AServiceException���X���[
		if(this.mMapFlg==false && mSetterMethod == null){
			throw new InvalidConfigurationException(this.getClass().getName()+ " Not Found " + nameAttr + " method.");
		}
		
	}
	/**
	 * value�����̒l���Z�b�g����B
	 */
	protected void setValue(String value){
		mValue = value;
	}

	/**
	 * value������Ԃ�
	 */
	protected String getValue() {
		return mValue;
	}

	/**
	 * "�Z�b�^�[���[�h"�̃Z�b�^�[
	 * @param mode String
	 */
	protected void setSetterMode(String mode) {
		if(mode.equals(C_VALUE_MOD_STR)){
			mMode = C_VALUE_MOD;
		}
		else if(mode.equals(C_SERVICE_MOD_STR)){
			mMode = C_SERVICE_MOD;
		}
		else if(mode.equals(C_STEP_MOD_STR)){
			mMode = C_STEP_MOD;
		}
		else if(mode.equals(C_RESOURCE_MOD_STR)){
			mMode = C_RESOURCE_MOD;
		}
		else if(mode.equals(C_INPUT_MOD_STR)){
			mMode = C_INPUT_MOD;
		}
		else{
			throw new ServiceException("StepParamInformation","It's not valid mode as setter [" + mode + "]");
		}
	}
	
	/**
	 * "�Z�b�^�[���[�h"�̃Q�b�^�[
	 * @return String
	 */
	protected int getSetterMode() {
		return mMode;
	}
	
	
	/**
	 * �Z�b�^�[���̃Q�b�^�[
	 * Returns the setterName.
	 * @return String
	 */
	protected String getSetterName() {
		return mSetterName;
	}

	/**
	 * �Z�b�^�[���̃Z�b�^�[
	 * @param setterName The setterName to set
	 */
	protected void setSetterName(String setterName) {
		mSetterName = setterName;
	}
	
	
	
	/**
	 * ���s���ɃR�[�������BBL�̃Z�b�^�[��invoke���郁�\�b�h
	 * @param invokeInstance
	 * @param execBlInstanceHash
	 * @param rm
	 * @param inputObj
	 */
	
	protected void invokeParameter(Object invokeInstance,
									HashMap execBlInstanceHash,
									ResourceManager rm,Object inputObj)
					throws NoSuchMethodException,InvocationTargetException,IllegalAccessException{
		Object retObj = null;

		if(invokeInstance == null){
			throw new ServiceException(this.getClass().getName(),"Target Object is null.") ;			
		}
		//���[�h�ɂ���ē����؂�ւ���B
		switch(mMode){
			case C_VALUE_MOD:
				//���l�������炻�̂܂ܐݒ肷��B
				//�^�[�Q�b�gBL�̃Z�b�^�[���s
				invokeSetter(invokeInstance,mValue);
				break;
			case C_SERVICE_MOD:
				//�T�[�r�X���[�h��������T�[�r�X�}�l�[�W�����g���ăT�[�r�X���擾����B
				mEditor.setAsText(mValue);
				retObj = ServiceManagerFactory.getServiceObject((ServiceName)mEditor.getValue()) ;
				//�^�[�Q�b�gBL�̃Z�b�^�[invoke
				invokeSetter(invokeInstance,retObj);
				break;
			case C_STEP_MOD:
				//�X�e�b�v���[�h��������
				if(this.mStepMethod != null){
					//BL�̃C���X�^���X���擾����B
					Object obj = execBlInstanceHash.get(mRefStepName);
					retObj = mStepMethod.invoke(obj,(Object[])null);
				}else{
					retObj = execBlInstanceHash.get(mRefStepName) ;
				}
				//�^�[�Q�b�gBL�̃Z�b�^�[���s
				invokeSetter(invokeInstance,retObj);					
				break;
			case C_RESOURCE_MOD:
				//���\�[�X���[�h�������烊�\�[�X�}�l�[�W���Ƀ��\�[�X���Ƃ��Ă�������B
				Object tmpObj = rm.getResource(mValue);
				//�Z�b�^�[���g�����U�N�V�������\�[�X�����҂��Ă�����E�E�E�E
				if( mIsTransactionResourceSetter ){
					retObj = tmpObj;
				}
				else{
					retObj = ((TransactionResource)tmpObj).getObject();
				}
				//�^�[�Q�b�gBL�̃Z�b�^�[���s
				invokeSetter(invokeInstance,retObj);
				break;
			case C_INPUT_MOD:
				//�C���v�b�g���[�h���������4�������Z�b�g����B
				if(this.mValue != null && this.mValue.length()>0){
					if(inputObj.getClass().isAssignableFrom(Map.class)){
						Map tmp =(Map)inputObj ;
						inputObj = tmp.get(mValue) ;
					}else{
						Class clazz1 = inputObj.getClass();
						Method method1 = null;
						method1 = clazz1.getMethod(mValue,(Class[])null);
						inputObj = method1.invoke(inputObj,(Object[])null);
					}
				}
				invokeSetter(invokeInstance,inputObj);
			default:
				//�Z�b�^�[�Ŗ����l���͂����Ă���̂ł����ɂ͂��Ȃ��B
				break;	
		}
	}

	/**
	 * ���\�b�h�����s����B
	 * @param invokeTarget
	 * @param argObj
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void invokeSetter(Object invokeTarget,Object argObj)
			throws NoSuchMethodException,
					IllegalAccessException,
					InvocationTargetException{	
		//�^�[�Q�b�gBL�̃Z�b�^�[���s
		Object objAry[] = new Object[1] ;
		objAry[0] = argObj ;
		Method msd = null ; 
		Journal jnl = this.invoker.getJournal() ;
		if(jnl != null){
			if(argObj != null){
				jnl.addInfo(this.mSetterName ,argObj.toString()) ;
			}else{
				jnl.addInfo(this.mSetterName ,C_VALUE_NULL) ;
			}
		}
		//�e�L�X�g����Z�b�^�[���~������N���X�֕ϊ�
		if(mMode == C_VALUE_MOD){
			if(this.mParamHash.size()==1){
				Set keys = this.mParamHash.keySet() ;
				for(Iterator ite = keys.iterator();ite.hasNext();){
					Class cls = (Class) ite.next() ;
					PropertyEditor pe = mCallBack.findPropEditor(cls) ;
					pe.setAsText((String)argObj) ;
					objAry[0] = pe.getValue() ;
					msd = (Method)this.mParamHash.get(cls) ;
					break ;
				}
			}
		//INPUT�̂Ƃ��͌^���킩��Ȃ��̂ōŏ��Ɍ����������Ŏ��s���邵���Ȃ�	
		//Primitive���^�s���̂���
		}else if(mMode == C_INPUT_MOD){
			Set keys = this.mParamHash.keySet() ;
			for(Iterator ite = keys.iterator();ite.hasNext();){
				Class cls = (Class) ite.next() ;
				msd = (Method)this.mParamHash.get(cls) ;			
				break ;
			}
		}else{
			if(this.mParamHash.size()>0){
				if(argObj!=null){
					msd = (Method)this.mParamHash.get(argObj.getClass()) ;
					if(msd == null){
						Set keys = this.mParamHash.keySet() ;
						for(Iterator ite = keys.iterator();ite.hasNext();){
							Class cls = (Class) ite.next() ;
							if(cls.isAssignableFrom(argObj.getClass())){
								msd = (Method)this.mParamHash.get(cls) ;			
								break ;
							}
						}
					}
				}else{
					Set keys = this.mParamHash.keySet() ;
					for(Iterator ite = keys.iterator();ite.hasNext();){
						Class cls = (Class) ite.next() ;
						msd = (Method)this.mParamHash.get(cls) ;			
						break ;
					}
				}
			}
		}
		if(msd == null && this.mMapFlg){
			Map map = (Map)invokeTarget ;
			map.put(this.mSetterName,argObj) ;
		}else if(msd != null){
			msd.invoke(invokeTarget,objAry);
		}else{
			throw new InvalidConfigurationException("Fail to set Attribute ["
										 + mSetterName + "]" + " param class is " + argObj.getClass());
		}
	}
	/**
	 * �K���w�肳��Ă��Ȃ���΂Ȃ�Ȃ��������擾���郁�\�b�h�B
	 * �����擾�Ɏ��s����ƁAServiceException�𓊂���B
	 */
	private String getAttMustBeSpecified(Element elem,String attName){
		String ret = elem.getAttribute(attName);
		if (ret != null){
			if(ret.length() > 0){
				return ret;
			}
		}
		throw new InvalidConfigurationException("Fail to get Attribute ["
									 + attName + "] ." + "Tag name is " + elem.getTagName());
	}

}
