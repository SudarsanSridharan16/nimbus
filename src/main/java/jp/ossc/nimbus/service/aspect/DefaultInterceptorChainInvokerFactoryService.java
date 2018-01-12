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
package jp.ossc.nimbus.service.aspect;
//�C���|�[�g
import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.reflect.*;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aspect.interfaces.*;
import jp.ossc.nimbus.service.aspect.metadata.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import jp.ossc.nimbus.service.aspect.util.*;
import jp.ossc.nimbus.service.log.*;
/**
 * �C���^�[�Z�v�^�[�`�F�[���C���{�[�J�[�t�@�N�g���[�T�[�r�X�N���X<p>
 * InterceptorChainInvoker�𐶐�����
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class DefaultInterceptorChainInvokerFactoryService
	extends ServiceBase
	implements DefaultInterceptorChainInvokerFactoryServiceMBean, 
				InterceptorChainInvokerFactory {
	
    private static final long serialVersionUID = 6716744038966389661L;
    
    /** �C���^�[�Z�v�^���X�g�L���b�V���̃}�b�v */
	private Map mInterceptListCacheMap = Collections.synchronizedMap(new HashMap());
	/** �C���^�[�Z�v�^��`���X�g */
	private List mInterceptConfigList = new ArrayList();
	/** �C���^�[�Z�v�^��`�t�@�C���� */
	private String[] mInterceptConfigFileNames = null;
	/** �C���^�[�Z�v�^�`�F�C���R�[���o�b�N���\�b�h */
	private Method mMethod = null;
	/** �R�[���o�b�N�N���X�� */
	private String mCallbackClassName = null;
	/** �R�[���o�b�N���\�b�h�� */
	private String mCallbackMethodName = null;
	/** �R�[���o�b�N���\�b�h�p���[���^�N���X���z�� **/
	private String[] mCallbackMethodParamClassNames = null;
	/** InterceptorInvoker�N���X�� */
	private String mInterceptorInvokerClassName = "jp.ossc.nimbus.service.aspect.InterceptorChainInvokerAccessImpl";
	/**	InterceptorInvoker�N���X */
	private Class mInterceptorPerfomerCls = null ;
	/**	Logger �T�[�r�X�� */
	private ServiceName mLoggerName = null ;
	/**	Logger �T�[�r�X */
	private Logger mLogger = null ;
	/**
	 * �R���X�g���N�^�[
	 */
	public DefaultInterceptorChainInvokerFactoryService() {
		super();
	}
	/* (�� Javadoc)
	 * �C���^�[�Z�v�^�R���|�[�l���g�̋N��<br>
	 * @see jp.ossc.nimbus.core.ServiceBaseSupport#startService()
	 */
	//
	public void startService() throws InvalidConfigurationException{
		if(this.mLoggerName != null){
			mLogger=(Logger)ServiceManagerFactory.getServiceObject(this.mLoggerName) ;
		}else{
			mLogger = getLogger();
		}
		completeMethod();
		loadConfig();
		mInterceptorPerfomerCls = findClazz(this.mInterceptorInvokerClassName) ;
		try {
            mInterceptorPerfomerCls.newInstance() ;
		} catch (InstantiationException e) {
			throw new InvalidConfigurationException(e) ;
		} catch (IllegalAccessException e) {
			throw new InvalidConfigurationException(e) ;
		}
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#setLoggerServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public void setLoggerServiceName(ServiceName name) {
		mLoggerName = name ;
	}

	/**
	 * ���\�b�h������������B<br>
	 */
	private void completeMethod() throws InvalidConfigurationException{
		if(this.mLogger != null){
			this.mLogger.write("AOP__00001",mCallbackClassName) ;
		}
		if(mCallbackClassName == null || mCallbackClassName.length() == 0){
			throw new InvalidConfigurationException("CallbackClassName is null");
		}
		if(this.mLogger != null){
			this.mLogger.write("AOP__00002",mCallbackMethodName) ;
		}
		//���[�g���s���\�b�h�擾
		if(mCallbackMethodName == null || mCallbackMethodName.length() == 0){
			throw new InvalidConfigurationException("CallbackMethodName is null");
		}
		try{
			//���[�g���s�N���X�擾
			final Class clazz = findClazz(mCallbackClassName);
			Class[] params = null;
			if(mCallbackMethodParamClassNames != null && mCallbackMethodParamClassNames.length != 0){
				params = new Class[mCallbackMethodParamClassNames.length];
				for(int icnt = 0; icnt < mCallbackMethodParamClassNames.length; icnt++){
					final String pClassName = mCallbackMethodParamClassNames[icnt];
					if(pClassName == null || pClassName.length() == 0){
						throw new InvalidConfigurationException("CallbackParameterClassName" + "[" + icnt +"] is null");
					}
					final Class pClazz = findClazz(pClassName);
					params[icnt] = pClazz;
					if(this.mLogger != null){
						this.mLogger.write("AOP__00003",pClassName) ;
					}

				}
			}
			mMethod = clazz.getMethod(mCallbackMethodName, params);
		}catch(NoSuchMethodException ex){
			throw new InvalidConfigurationException(ex);
		}catch(SecurityException es){
			throw new InvalidConfigurationException(es);
		}
		if(this.mLogger != null){
			this.mLogger.write("AOP__00004") ;
		}
	}
	/**
	 * �w�肵���N���X���̃N���X�I�u�W�F�N�g���擾����<br>
	 * @param  String				�N���X��
	 * @return Class				�N���X�I�u�W�F�N�g
	 */
	private Class findClazz(String className) throws InvalidConfigurationException{
		Class clazz = null;
		try{
			clazz = Class.forName(
				className,
				true,
				NimbusClassLoader.getInstance()
			);
		}catch(ClassNotFoundException ex){
			throw new InvalidConfigurationException(className + " is Invalid Class" , ex) ;
		}
		return clazz ;
	}

    /**
     * Logger��ݒ肷��B
     */
	public void setLogger(Logger logger) {
		mLogger = logger;
	}
	
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#setInterceptorConfigFileNames(java.lang.String[])
	 */
	public void setInterceptorConfigFileNames(String[] fileNames) {
		this.mInterceptConfigFileNames = fileNames ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#getInterceptorConfigFileNames()
	 */
	public String[] getInterceptorConfigFileNames() {
		return this.mInterceptConfigFileNames ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#setCallbackClassName(java.lang.String)
	 */
	public void setCallbackClassName(String callbackClassName) {
		this.mCallbackClassName = callbackClassName ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#getCallbackClassName()
	 */
	public String getCallbackClassName() {
		return this.mCallbackClassName;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#setCallbackMethodName(java.lang.String)
	 */
	public void setCallbackMethodName(String callbackMethodName) {
		this.mCallbackMethodName = callbackMethodName ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#getCallbackMethodName()
	 */
	public String getCallbackMethodName() {
		return this.mCallbackMethodName ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#setCallbackMethodParamClassNames(java.lang.String[])
	 */
	public void setCallbackMethodParamClassNames(String[] callbackMethodParamClassNames) {
		this.mCallbackMethodParamClassNames  = callbackMethodParamClassNames ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#getCallbackMethodParamClassNames()
	 */
	public String[] getCallbackMethodParamClassNames() {
		return this.mCallbackMethodParamClassNames ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#setInterceptorInvokerClassName(java.lang.String)
	 */
	public void setInterceptorInvokerClassName(String interceptorInvokerClassName) {
		this.mInterceptorInvokerClassName = interceptorInvokerClassName ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#getInterceptorInvokerClassName()
	 */
	public String getInterceptorInvokerClassName() {
		return this.mInterceptorInvokerClassName;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.DefaultInterceptorChainInvokerFactoryServiceMBean#loadConfig()
	 */
	public void loadConfig() 
		throws InvalidConfigurationException {
		if(mInterceptConfigFileNames == null || mInterceptConfigFileNames.length == 0){
			throw new InvalidConfigurationException("InterceptConfigFileNames is null");
		}
		InputStream fis = null;
		try{
			for(int icnt = 0; icnt < mInterceptConfigFileNames.length; icnt++){
				if(this.mLogger != null){
					this.mLogger.write("AOP__00005",mInterceptConfigFileNames[icnt]) ;
				}
				URL url = Thread.currentThread().getContextClassLoader().getResource(mInterceptConfigFileNames[icnt]);
				if(url == null){
					throw new InvalidConfigurationException("interceptor config " + mInterceptConfigFileNames[icnt] + "is none ") ;
				}
				fis = url.openStream();
				final DocumentBuilderFactory domFactory
					   = DocumentBuilderFactory.newInstance();
				final DocumentBuilder builder = domFactory.newDocumentBuilder();
				final Document doc = builder.parse(fis);
				InterceptorMappingsMetaData imsmd = new InterceptorMappingsMetaData();
				imsmd.importXML(doc.getDocumentElement());
				loadInterceptConfigList(mInterceptConfigList, imsmd);
				fis.close();
				fis = null;
				if(this.mLogger != null){
					this.mLogger.write("AOP__00006",mInterceptConfigFileNames[icnt]) ;
				}
			  }
		//} catch (FileNotFoundException e) {
		//	throw new InvalidConfigurationException(e) ;
		} catch (ParserConfigurationException e) {
			throw new InvalidConfigurationException(e) ;
		} catch (SAXException e) {
			throw new InvalidConfigurationException(e) ;
		} catch (IOException e) {
			throw new InvalidConfigurationException(e) ;
		} catch (DeploymentException e) {
			throw new InvalidConfigurationException(e) ;
		}finally{
			if(fis != null){
				try{
					fis.close();
				}catch(Throwable e){}
				 fis = null;
			  }
		  }
	}
	/**
	 * �C���^�[�Z�v�^��`�t�@�C����ǂݍ���<br>
	 * @param List							��`���i�[���郊�X�g
	 * @param InterceptorMappingsMetaData	XML���^�f�[�^
	 * @exception IOException
	 * @exception ParserConfigurationException
	 * @exception SAXException
	 * @exception DeploymentException
	 * @exception FrameworkException
	 */
	private void loadInterceptConfigList(List list, InterceptorMappingsMetaData imsmd)
	  throws IOException, ParserConfigurationException, SAXException, DeploymentException{
		final List immdList = imsmd.getInterceptorMappingList();
		if(immdList == null || immdList.size() == 0){
			// ����I��
			return;
		}
		for(int icnt = 0; icnt < immdList.size(); icnt++){
			final InterceptorPaternConfig ic = new InterceptorPaternConfig();
			final InterceptorMappingMetaData immd = (InterceptorMappingMetaData)immdList.get(icnt);
			final InterceptorNameMetaData inmd = immd.getInterceptorName();
			if(inmd == null){
				// �t���[�����[�N��O���X���[
				throw new DeploymentException("<interceptor-name> is not found");
			}
			final String interceptorNameStr = inmd.getInterceptorName();
			final ServiceName interceptorName = UtilTool.convertServiceName(interceptorNameStr);
			if(interceptorName == null){
				// �t���[�����[�N��O���X���[
				throw new DeploymentException("<interceptor-name>[CONTENTS]</interceptor-name> is not found");
			}
			// �Y���̃R���|�[�l���g���Ȃ��ꍇ
			if(UtilTool.getInterceptor(interceptorName) == null){
				throw new DeploymentException("<interceptor-name>[CONTENTS]</interceptor-name> is missing");
			}
			ic.setInterceptorServiceName(interceptorName);
			final PatternsMetaData psmd = immd.getPatterns();
			// �p�^�[��s���Ȃ��ꍇ
			if(psmd == null){
				// �t���[�����[�N��O���X���[
				throw new DeploymentException("<patterns>[CONTENTS]</patterns> is not found");
			}
			final List ptList = psmd.getPatternList();
			// �p�^�[�����Ȃ��ꍇ
			if(ptList == null || ptList.size() == 0){
				// �t���[�����[�N��O���X���[
				throw new DeploymentException("<pattern>[CONTENTS]</pattern> is not found");
			}
			final String[] patterns = new String[ptList.size()];
			for(int jcnt = 0; jcnt < ptList.size(); jcnt++){
				final PatternMetaData pmd = (PatternMetaData)ptList.get(jcnt);
				patterns[jcnt] = pmd.getPattern();
			}
			ic.setPatterns(patterns);
			// �C���^�[�Z�v�^��`���i�[
			list.add(ic);
		}
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.InterceptorChainInvokerFactory#createInterceptorInvoker(java.lang.String)
	 */
	public InterceptorChainInvoker createInterceptorInvoker(String chainKey) {
		IntreceptorChainList list = null;
		// �G�C���A�X�ɑΉ�����C���^�[�Z�v�^���X�g���L���b�V���ɂ��邩
		if(mInterceptListCacheMap.containsKey(chainKey)){
			// ���݂����ꍇ�̓L���b�V���}�b�v����C���^�[�Z�v�^���X�g���擾
			list = (IntreceptorChainList)mInterceptListCacheMap.get(chainKey);
		}else{
			// ���݂��Ȃ��ꍇ
			// �G�C���A�X����уT�[�r�X����C���^�[�Z�v�^���X�g�쐬
			list = findMatchedInterceptorChainList(chainKey);
			// �쐬�����C���^�[�Z�v�^���X�g���L���b�V���}�b�v�Ɋi�[
			mInterceptListCacheMap.put(chainKey, list);
		}
		// �C���^�[�Z�v�^���X�g����C���^�[�Z�v�^���s�I�u�W�F�N�g(�C���^�[�Z�v�^�`�F�C��)���쐬
		final InterceptorChainInvokerAccess ich =  createInterceptorInvokerAccess(list);
		return ich;
	}
	/**
	 * �p�t�H�[�}�[���쐬����
	 * @param list	IntreceptorChainList
	 * @return	InterceptorChainInvokerAccess
	 */
	private InterceptorChainInvokerAccess createInterceptorInvokerAccess(IntreceptorChainList list) {
		InterceptorChainInvokerAccess object = null;
		try{
			object = (InterceptorChainInvokerAccess)this.mInterceptorPerfomerCls.newInstance();
		}catch(InstantiationException ex){
			//createServie�Ŏ�������
		}catch(IllegalAccessException ex){
			//createServie�Ŏ�������
		}
		object.setLogger(this.mLogger) ;
		object.setInterceptorChainList(list) ;
		object.setCallBackmethod(this.mMethod) ;
		return (InterceptorChainInvokerAccess)object;
	}

	/**
	 * �G�C���A�X�ɑΉ�����C���^�[�Z�v�^�̃��X�g���쐬���ԋp<br>
	 * @param  String					�G�C���A�X
	 * @return IntreceptorChainList			�C���^�[�Z�v�^���X�g
	 */
	private IntreceptorChainList findMatchedInterceptorChainList(String key){
		// �C���^�[�Z�v�^���X�g�쐬
		final IntreceptorChainList list = new IntreceptorChainList();
		// �C���^�[�Z�v�^��`���X�g�������Q��
		for(final Iterator ite = mInterceptConfigList.iterator(); ite.hasNext();){
			// �C���^�[�Z�v�^��`���擾
			final InterceptorPaternConfig interceptConfig = (InterceptorPaternConfig)ite.next();
			// �p�^�[���}�b�`���O�m�F
			if(interceptConfig.isMatch(key)){
				// �}�b�`�����ꍇ�C���^�[�Z�v�^���C���^�[�Z�v�^���X�g�Ɋi�[
				if(this.mLogger != null){
					String ary[] = new String[2] ;
					ary[0] = key ;
					ary[1] = interceptConfig.getInterceptorServiceName().toString() ;
					this.mLogger.write("AOP__00007",ary) ;
				}
				list.add(interceptConfig.getInterceptorServiceName());
			}
		}
		return list;
	}

}
