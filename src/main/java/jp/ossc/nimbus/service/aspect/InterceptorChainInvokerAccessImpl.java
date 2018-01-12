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
import java.lang.reflect.*;
import java.util.*;
import jp.ossc.nimbus.service.aspect.interfaces.*;
import jp.ossc.nimbus.service.log.*;
import jp.ossc.nimbus.service.aspect.util.*;
import jp.ossc.nimbus.core.*;

/**
 * �C���^�[�Z�v�^�[���s�N���X<p>
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class InterceptorChainInvokerAccessImpl
	implements InterceptorChainInvoker, InterceptorChainInvokerAccess {
	// �C���^�[�Z�v�^���X�g
	protected IntreceptorChainList mList = null;
	// ���X�g�C�e���[�^
	protected Iterator mIte = null;
	// �R�[���o�b�N�Ώۂ̃I�u�W�F�N�g
	protected Object mCallBackObject = null;
	// �R�[���o�b�N���\�b�h
	protected Method mCallBackmethod = null;
	protected Logger mLogger = null; 
	/**
	 * �R���X�g���N�^
	 */
	public InterceptorChainInvokerAccessImpl(){
		super();
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.InterceptorChainInvokerAccess#setLogger(jp.ossc.nimbus.service.log.Logger)
	 */
	public void setLogger(Logger logger){
		this.mLogger = logger ;		
	} 

	/**
	 * �C���^�[�Z�v�^���X�g�ԋp<br>
	 * @return IntreceptorChainList			�C���^�[�Z�v�^�̃��X�g���܂܂��I�u�W�F�N�g
	 */
	protected IntreceptorChainList getInterceptorChainList(){
		return this.mList;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.interfaces.InterceptorChainInvoker#invokeChain(java.lang.Object, java.lang.Object)
	 */
	public Object invokeChain(Object callBackObject, Object inputObj)
		throws
			InterceptorException,
			TargetCheckedException,
			TargetUncheckedException {
		if(mList == null){
			if(mLogger != null){
				mLogger.write("AOP__00013");
			}
			throw new InterceptorException("InterceptorChainList is null");
		}
		if(callBackObject == null){
			if(mLogger != null){
				mLogger.write("AOP__00014");
			}
			throw new InterceptorException("callBackObject is null");
		}
		if(mCallBackmethod == null){
			if(mLogger != null){
				mLogger.write("AOP__00015");
			}
			throw new InterceptorException("CallBackmethod is null");
		}
		if(mLogger != null){
			mLogger.write("AOP__00008");
		}
		// �R�[���o�b�N�I�u�W�F�N�g��ێ�
		mCallBackObject = callBackObject;
		// Iterator�쐬
		if(mLogger != null){
			mLogger.write("AOP__00009",mList.size());
		}
		mIte = mList.iterator();
		
		// �`�F�C�����s
		Object retObject = null ;
		try{
			retObject = invokeChain(inputObj);
			
		}catch(TargetUncheckedException e){
			if(mLogger != null){
				mLogger.write("AOP__00016",e) ;
			}
			throw e ;
		}catch(TargetCheckedException e){
			if(mLogger != null){
				mLogger.write("AOP__00017",e);
			}	
			throw e ;		
		}catch(InterceptorException e){
			if(mLogger != null){
				mLogger.write("AOP__00020",e);
			}			
			throw e ;		
		}
		
		return retObject;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.interfaces.InterceptorChain#invokeChain(java.lang.Object)
	 */
	public Object invokeChain(Object inputObj) 
		throws InterceptorException, 
				TargetCheckedException,
				TargetUncheckedException{
		Object retObject = null;
		// �C���^�[�Z�v�^�̃C�e���[�^����L���m�F
		if(mIte.hasNext()){
			// �R���|�[�l���g��(�C���^�[�Z�v�^��)���擾
			final ServiceName name = (ServiceName)mIte.next();
			// �R���|�[�l���g���ɑΉ�����C���^�[�Z�v�^���擾
			final Interceptor interceptor = UtilTool.getInterceptor(name);
			// �C���^�[�Z�v�^�C���X�^���X��null�̂Ƃ�
			if(interceptor == null){
				// ��O����
				throw new InterceptorException("interceptor[" + name + "] is null");
			}
			if(mLogger != null){
				mLogger.write("AOP__00010",name);
			}
			try{
				retObject = interceptor.invokeChain(inputObj, this);	
			}catch(TargetUncheckedException e){
				if(mLogger != null){
					mLogger.write("AOP__00016",e) ;
				}
				throw e ;
			}catch(TargetCheckedException e){
				if(mLogger != null){
					mLogger.write("AOP__00017",e);
				}	
				throw e ;		
			}catch(InterceptorException e){
				if(mLogger != null){
					mLogger.write("AOP__00020",e);
				}			
				throw e ;		
			}
			
		}else{
			try{
				if(mLogger != null){
					mLogger.write("AOP__00011",this.mCallBackmethod.getName());
				}
				retObject = mCallBackmethod.invoke(mCallBackObject,
				  new Object[]{inputObj}
				);
				if(mLogger != null){
					mLogger.write("AOP__00012",this.mCallBackmethod.getName());
				}
			}catch(InvocationTargetException e){
				Throwable cause = e.getCause() ;
				if(cause instanceof RuntimeException ){
					if(mLogger != null){
						mLogger.write("AOP__00016",cause);
					}
					throw new TargetUncheckedException(cause) ;			
				}else if(cause instanceof InterceptorException ){
					throw (InterceptorException)cause ;			
				}else if(cause instanceof Exception ){
					if(mLogger != null){
						mLogger.write("AOP__00017",cause);
					}
					throw new TargetCheckedException(cause) ;			
				}else{
					if(mLogger != null){
						mLogger.write("AOP__00018",cause);
					}
					throw new TargetUncheckedException(cause) ;			
				}
			}catch(IllegalArgumentException e) {
				// FrameworkException�ɓ���X���[
				if(mLogger != null){
					mLogger.write("AOP__00019",e);
				}
				throw new InterceptorException("root callback error",e);
			}catch(IllegalAccessException e) {
				// FrameworkException�ɓ���X���[
				if(mLogger != null){
					mLogger.write("AOP__00020",e);
				}
				throw new InterceptorException("root callback error",e);
			}catch(Throwable e){
				if(mLogger != null){
					mLogger.write("AOP__00021",e);
				}
				throw new InterceptorException("root callback error",e);
			}
		}
		return retObject;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.InterceptorChainInvokerAccess#setInterceptorChainList(jp.ossc.nimbus.service.aspect.IntreceptorChainList)
	 */
	public void setInterceptorChainList(IntreceptorChainList interceptorChainList) {
		this.mList = interceptorChainList ;
	}

	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.aspect.InterceptorChainInvokerAccess#setCallBackmethod(java.lang.reflect.Method)
	 */
	public void setCallBackmethod(Method callBackmethod) {
		this.mCallBackmethod = callBackmethod ;
	}
	/**
	 * �R�[���o�b�N�Ώۂ̃��\�b�h��ԋp<br>
	 * �C���^�[�Z�v�^�Ǘ��R���|�[�l���g����Ăяo�����B
	 * @return Method					�R�[���o�b�N�Ώۂ̃��\�b�h
	 */
	protected Method getCallBackmethod(){
		return mCallBackmethod;
	}

}
