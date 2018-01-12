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
package jp.ossc.nimbus.service.cui;

import jp.ossc.nimbus.beans.*;
import jp.ossc.nimbus.core.*;
import java.util.*;
import java.io.*;
import jp.ossc.nimbus.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import jp.ossc.nimbus.lang.*;
/**
 *	�R�}���h���̓T�[�r�X
 *	��`�t�@�C����ǂݍ��݁A�R�}���h���̓I�u�W�F�N�g�𐶐�����B
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/10/20�| y-tokuda<BR>
 *				�X�V�F
 */
public class CuiFactoryService
	extends ServiceBase
	implements CuiFactoryServiceMBean,
				CuiTagDefine,CuiFactory {
	
    private static final long serialVersionUID = -3162235897050844253L;
    
    //�����o�ϐ�
	/** �R�}���h���͒�`�t�@�C���z�u�f�B���N�g���@*/
	private String mDefFileDir = null;
	/** �R�}���h���͒�`�t�@�C���g���q */
	private String mDefFileExt = null;
	/** �R�}���h���̓I�u�W�F�N�g�̃n�b�V�� */
	private HashMap mCuiHash = null;
	/** Cui�����N���X�� */
	private String  mImplementClassName = null;
	/**
	 * �R���X�g���N�^�[
	 */
	public CuiFactoryService() {
		super();
	}
	/**
	 * CuiOperator�����N���X���Z�b�^�[
	 */
	public void setImplementClassName(String name){
		mImplementClassName = name;
	}
	/**
	 * CuiOperator�����N���X���Q�b�^�[
	 */
	public String getImplementClassName(){
		return mImplementClassName;
	}	
	/**
	 * ����
	 */
	public void createService(){
		mCuiHash = new HashMap();
	}
	/**
	 * �N��
	 * 
	 */
	public void startService(){
		//��`�t�@�C����T���A���[�h����B
		File DefDir = new File(mDefFileDir);
		ExtentionFileFilter filter = new ExtentionFileFilter(mDefFileExt);
		File[] defFileList = DefDir.listFiles(filter);
		//���ׂĂ̒�`�t�@�C����Open
		if(defFileList != null){
			for(int rCnt=0;rCnt<defFileList.length;rCnt++){
				//�p�[�X����B
				parse(defFileList[rCnt]);
			}
		}
	}
	/**
	 * ��~
	 * 
	 * 
	 */
	public void stopService(){	
	}
	/**
	 * �j��
	 */
	public void destroy(){
		mCuiHash = null;
	}
    /**
     * �O��AP��Cui�C���X�^���X��񋟂���B
     * @param key
     * @return Cui
     */
	public Cui findInstance(String key){
		return (Cui)mCuiHash.get(key);
	}
	/**
	 * �R�}���h���͒�`�t�@�C���f�B���N�g���Z�b�^�[
	 *
	 */
	public void setDefFileDir(String dir){
		mDefFileDir = dir;
	}
	/**
	 * �R�}���h���͒�`�t�@�C���g���q�Z�b�^�[
	 *
	 */
	public void setDefFileExtention(String ext){
		mDefFileExt = ext;
	}
	/**
	 * �R�}���h���͒�`�t�@�C���f�B���N�g���Q�b�^�[
	 *
	 */
	public String getDefFileDir(){
		return mDefFileDir;
	}
	/**
	 * �R�}���h���͒�`�t�@�C���g���q�Q�b�^�[
	 *
	 */
	public String getDefFileExtention(){
		return mDefFileExt;
	}
	/**
	 * XML��`�t�@�C���p�[�X���\�b�h
	 */
	public void parse(File xmlFile) {
		Element root = getRoot(xmlFile);
		// dataInput�G�������g���擾
		NodeList dataInputList = root.getElementsByTagName(DATAINPUT_TAG);
		// ��`����Ă���dataInput�̐����[�v����B
		for(int rCnt=0;rCnt<dataInputList.getLength();rCnt++){
			Element dataInputElement = (Element)dataInputList.item(rCnt);
			//key���擾
			String key = dataInputElement.getAttribute(DATAINPUT_TAG_KEY_ATT);
			if (key.length() < 1){
				//�v���I�G���[
				throw new ServiceException("CUIFACTORY001","attribute name is none. tag name is"+ dataInputElement.getTagName());
			}
			//CuiOperator�I�u�W�F�N�g�𐶐�
			CuiOperator cuiObj = createCuiOperator();
			//step�v�f���擾
			NodeList steps = dataInputElement.getElementsByTagName(STEP_TAG);
			for(int rCount=0;rCount<steps.getLength();rCount++){
				Element stepElement = (Element)steps.item(rCount);
				String stepName = stepElement.getAttribute(STEP_TAG_NAME_ATT);
				if(stepName == null){
					//�v���I�G���[
					throw new ServiceException("CUIFACTORY002","Tag name is "+ stepElement.getTagName());
				}
				//DataInputStep�I�u�W�F�N�g��new
				DataInputStep dataInputStep = new DataInputStep(stepName);
				//DataInputStep�I�u�W�F�N�g��Display�I�u�W�F�N�g���Z�b�g
				setDisplayObject(dataInputStep,stepElement); 
				//DataInputStep�I�u�W�F�N�g��InputChecker�I�u�W�F�N�g�Z�b�g
				setInputChecker(dataInputStep,stepElement);
				//DataInputStep�I�u�W�F�N�g�ɑJ�ڐ�n�b�V�����Z�b�g
				setWhereToGoHash(dataInputStep,stepElement);
				NodeList ends = stepElement.getElementsByTagName(END_TAG);
				if(ends.getLength() > 1){
					//���step����end�v�f��2�ȏ゠������Exception��throw
					throw new ServiceException("CUIFACTORY003",END_MULTI_DEF_ERR + "Tag name is "+ stepElement.getTagName());		
				}
				if(ends.getLength() == 1){
					//end�錾������̂ŁADataInputObject�̎�Step�ɏI�����Z�b�g
					Element endElem = (Element)ends.item(0);
					String type_att = endElem.getAttribute(END_TAG_TYPE_ATT);
					String endMsg = getValueIfSpecified(endElem);
					if( type_att.equals(END_FORCE)){
						//����I���ݒ�
						dataInputStep.setNextStepName(INTERRUPT);
					}
					else{
						//�����I���ݒ�
						dataInputStep.setNextStepName(END);
					}
					//�I�����b�Z�[�W�ݒ�
					dataInputStep.setEndMessage(endMsg);
				}
				else{
					//end�錾����
					//�����܂ł̏����ŁADataInputStep�I�u�W�F�N�g�ɁA��Step���ݒ肳��Ă��Ȃ���Έȉ��̏���
					//���s���B
					if(dataInputStep.getNextStepName() == null){
						//�Ō��step�łȂ���Ύ��ɋL�q����Ă���X�e�b�v�����X�e�b�v�ɂ���B
						if(rCount != (steps.getLength() -1) ){
							Element nextStepElem = (Element)steps.item(rCount+1);
							String name_att = nextStepElem.getAttribute(STEP_TAG_NAME_ATT);
							dataInputStep.setNextStepName(name_att);
						}
						else{
							//�Ō�ɋL�q����Ă���X�e�b�v�ɂ́Aend�^�O���L�q���Ȃ��Ă悢���Ƃɂ���B
							dataInputStep.setNextStepName(END);
						}
					}
				}
			//DataInputStep�I�u�W�F�N�g��Cui�I�u�W�F�N�g�Ɋi�[����B
			cuiObj.addStep(stepName,dataInputStep);
			}
		//Cui�I�u�W�F�N�g���n�b�V���Ɋi�[����B
		mCuiHash.put(key,cuiObj);
		}
	}
	/**
	 * ���[�g�G�������g��Ԃ��B
	 * @param xmlFile
	 * @return�@Element
	 */
	protected Element getRoot(File xmlFile){
		DocumentBuilderFactory dbfactory;
		DocumentBuilder builder;
		Document doc;
		Element root;
		try{
			// �h�L�������g�r���_�[�t�@�N�g���𐶐�
			dbfactory = DocumentBuilderFactory.newInstance();
			// �h�L�������g�r���_�[�𐶐�
			builder = dbfactory.newDocumentBuilder();
			// �p�[�X�����s����Document�I�u�W�F�N�g���擾
			doc = builder.parse(xmlFile);
			// ���[�g�v�f���擾
			root = doc.getDocumentElement();
		}
		catch(Exception e){
			throw new ServiceException("CUIFACTORY021","Fail to get Document Root",e);
		}
		return root;
	}
	
	/**
	 * DataInputStep�I�u�W�F�N�g�ɁA�f�B�X�v���C�I�u�W�F�N�g��ݒ肷��B
	 * @param step
	 * @param stepElem
	 */
	protected void setDisplayObject(DataInputStep step,
									Element stepElem) {
		//display�v�f���擾
		NodeList displays = stepElem.getElementsByTagName(DISPLAY_TAG);
		//Display TAG���T�[�`
		for(int rCnt=0;rCnt<displays.getLength();rCnt++){
			Element display = (Element)displays.item(rCnt);
			String type = display.getAttribute(DISPLAY_TAG_TYPE_ATT);
			if (type.equals(DISPLAY_TYPE_SERVICE)){
				//�T�[�r�X����
				String name = getValueMustSpecified(display);
				ServiceNameEditor edit = new ServiceNameEditor() ;
				edit.setAsText(name) ;
				ServiceName serviceName = (ServiceName)edit.getValue();
				DisplayConstructer displayObject = (DisplayConstructer)ServiceManagerFactory.getServiceObject(serviceName) ;
				if (displayObject == null){
					throw new ServiceException("CUIFACTORY022","Service not found. servicename is "+ name);
				}
				//DataInputStep�I�u�W�F�N�g�ɐݒ�
				step.addDisplay(displayObject);				
			}else{
				//TextDisplay�𐶐�
				TextDisplay textDisplay = new TextDisplay();
				//�\���������Z�b�g
				textDisplay.setDisplayMenu(getValueIfSpecified(display)) ;
				//DataInputStep�I�u�W�F�N�g�ɐݒ�
				step.addDisplay(textDisplay);
			}
		}
	}
	/**
	 * DataInputStep�I�u�W�F�N�g�ɁAInputCheker�I�u�W�F�N�g��ݒ肷��B
	 * @param step
	 * @param stepElem
	 */
	protected void setInputChecker(DataInputStep step,Element stepElem){
		NodeList checkList = stepElem.getElementsByTagName(INPUT_TAG);
		if(checkList.getLength() > 1){
			throw new ServiceException("CUIFACTORY023","input tag dupulicate define");
		}
		for(int rCnt=0;rCnt<checkList.getLength();rCnt++){
			Element inputElem = (Element)checkList.item(rCnt);
			//�������`�F�b�N����B
			String type = inputElem.getAttribute(INPUT_TAG_TYPE_ATT);
			InputChecker checker = null;
			if(type.equals(INPUT_TYPE_SERVICE)){
				//�T�[�r�X�������ꍇ
				String name = getValueMustSpecified(inputElem);
				ServiceNameEditor edit = new ServiceNameEditor() ;
				edit.setAsText(name) ;
				ServiceName serviceName = (ServiceName)edit.getValue();
				checker = (InputChecker)ServiceManagerFactory.getServiceObject(serviceName) ;
				if (checker == null){
					throw new ServiceException("CUIFACTORY024","Service not found. servicename is "+ name);
				}
						
			}
			else{
				//�T�[�r�X�ł͂Ȃ������ꍇ�ATextInputChecker�𐶐�
				String inputDefStr = getValueMustSpecified(inputElem);
				checker = new TextInputChecker();
				((TextInputChecker)checker).setValidInput(inputDefStr);
			}
			//DataInputStep�I�u�W�F�N�g�ɐݒ�	
			step.setChecker(checker);	
		}
		//������checker���ݒ肳��Ă��Ȃ�������A�ǂ�Ȓl���L���l�Ƃ��ĕԂ��`�F�b�J�[��ݒ�B
		if(step.getChecker() == null){
			step.setChecker(new AnyValueOkChecker());	
		}
	}
	
	

	/**
	 * DataInputStep�I�u�W�F�N�g�ɑJ�ڐ���Z�b�g����B
	 * 
	 */
	protected void setWhereToGoHash(DataInputStep step,Element stepElem) {
		//�J�ڐ�n�b�V����ݒ肷��B
		NodeList gotolist =stepElem.getElementsByTagName(GOTO_TAG);
		for(int rCnt=0;rCnt<gotolist.getLength();rCnt++){
			Element gotoElem = (Element)gotolist.item(rCnt);
			String value_att = gotoElem.getAttribute(GOTO_TAG_VALUE_ATT);		
			if (value_att.length() < 1){
				String distination = getValueMustSpecified(gotoElem);
				//������GOTO�́A�n�b�V���ł͂Ȃ��A��STEP��ێ����郁���o�ϐ��ɃZ�b�g����B
				step.setNextStepName(distination);
			}
			else{
				//�����t��GOTO�̓n�b�V���Ɋi�[
				String distination = getValueMustSpecified(gotoElem);
				step.addWhereToGo(value_att,distination);
			}
		}
	}
	/**
	 * getFirstChild��null��Ԃ�����A�T�[�r�X�G�N�Z�v�V�����𓊂��郁�\�b�h
	 * �K���l���ݒ肳��Ă���ׂ�
	 * @return
	 */
	protected String getValueMustSpecified(Element elem){
		Node node = elem.getFirstChild();
		if(node == null){
			throw new ServiceException("CUIFACTORY030","must be specified value tag is " + elem.getTagName());
		}
		return node.getNodeValue();
	}
	/**
	 * getFirstChild��null��Ԃ�����A�󕶎���Ԃ����\�b�h
	 * 
	 */
	protected String getValueIfSpecified(Element elem){
		Node node = elem.getFirstChild();
		if(node == null){
			return "";
		}
		else{
			return node.getNodeValue();
		}
	}
	/**
	 * �ݒ肳�ꂽ�����N���X���̃C���X�^���X�𐶐�����B
	 * 
	 */
	protected CuiOperator createCuiOperator(){
		CuiOperator cuiOperator = null;
		try{
			cuiOperator = (CuiOperator)Class.forName(
				mImplementClassName,
				true,
				NimbusClassLoader.getInstance()
			).newInstance();
		}
		catch(IllegalAccessException e){
			throw new ServiceException("CUIFACTORY025","IllegalAccess When creatCuiOperator() ",e);
		}
		catch(InstantiationException e){
			throw new ServiceException("CUIFACTORY026","Instanting failed",e);
		}
		catch(ClassNotFoundException e){
			throw new ServiceException("CUIFACTORY027","Class not found. name is "+mImplementClassName);
		}
		return cuiOperator;
	}
		
}
