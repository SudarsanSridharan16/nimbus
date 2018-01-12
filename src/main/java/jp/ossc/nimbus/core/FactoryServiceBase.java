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
package jp.ossc.nimbus.core;

import java.util.*;

/**
 * �t�@�N�g���T�[�r�X���N���X�B<p>
 *
 * @author M.Takata
 */
public abstract class FactoryServiceBase extends ServiceBase
 implements FactoryServiceBaseMBean{
    
    private static final long serialVersionUID = -7723361215992951033L;

    /**
     * ���̃t�@�N�g���ɂ���Đ������ꂽ�I�u�W�F�N�g���Ǘ����邩�ǂ����������t���O�B<p>
     * �Ǘ�����ꍇ�Atrue�B<br>
     */
    protected volatile boolean isManaged;
    
    /**
     * ���̃t�@�N�g���ɂ���ăX���b�h�P�ʂɃI�u�W�F�N�g�𐶐����邩�ǂ����������t���O�B<p>
     * �X���b�h�P�ʂɐ�������ꍇ�Atrue�B<br>
     */
    protected volatile boolean isThreadLocal;
    
    /**
     * �Ǘ����Ă���C���X�^���X��ێ�����W���B<p>
     */
    protected Set managedInstances = Collections.synchronizedSet(new HashSet());
    
    /**
     * �X���b�h�P�ʂɊǗ����Ă���C���X�^���X��ێ�����ThreadLocal�B<p>
     */
    protected ThreadLocal threadLocal = new ThreadLocal();
    
    /**
     * �T�[�r�X�̊J�n���ɁA�t�@�N�g������I�u�W�F�N�g�̐��������݂Ă݂邩�ǂ����̃t���O�B<p>
     * �f�t�H���g�ł́Atrue�B<br>
     */
    protected boolean isCreateTemplateOnStart = true;
    
    /**
     * �R���X�g���N�^�B<p>
     */
    public FactoryServiceBase(){
        super();
    }
    
    // FactoryService��JavaDoc
    public void setManagement(boolean isManaged){
        this.isManaged = isManaged;
    }
    
    // FactoryService��JavaDoc
    public boolean isManagement(){
        return isManaged;
    }
    
    // FactoryService��JavaDoc
    public void setThreadLocal(boolean isThreadLocal){
        this.isThreadLocal = isThreadLocal;
    }
    
    // FactoryService��JavaDoc
    public boolean isThreadLocal(){
        return isThreadLocal;
    }
    
    // FactoryService��JavaDoc
    public void release(Object service){
        if(managedInstances.size() != 0){
            managedInstances.remove(service);
        }
        threadLocal.set(null);
    }
    
    // FactoryService��JavaDoc
    public void release(){
        final Object[] instances = managedInstances.toArray();
        for(int i = 0; i < instances.length; i++){
            release(instances[i]);
        }
        threadLocal = new ThreadLocal();
    }
    
    // FactoryService��JavaDoc
    public Object newInstance(){
        Object obj = null;
        if(isManaged){
            synchronized(managedInstances){
                if(isThreadLocal){
                    obj = threadLocal.get();
                }
                if(obj == null){
                    try{
                        obj = createInstance();
                    }catch(Exception e){
                        return null;
                    }
                    if(isThreadLocal){
                        threadLocal.set(obj);
                    }
                }
                managedInstances.add(obj);
            }
        }else{
            if(isThreadLocal){
                obj = threadLocal.get();
            }
            if(obj == null){
                try{
                    obj = createInstance();
                }catch(Exception e){
                    return null;
                }
                if(isThreadLocal){
                    threadLocal.set(obj);
                }
            }
        }
        return obj;
    }
    
    // FactoryServiceBaseMBean��JavaDoc
    public void setCreateTemplateOnStart(boolean isCreate){
        isCreateTemplateOnStart = isCreate;
    }
    
    // FactoryServiceBaseMBean��JavaDoc
    public boolean isCreateTemplateOnStart(){
        return isCreateTemplateOnStart;
    }
    
    /**
     * ���̃t�@�N�g���Ő��������C���X�^���X�̓��A�Ǘ����Ă���C���X�^���X�̏W�����擾����B<p>
     *
     * @return �Ǘ����Ă���C���X�^���X�̏W��
     */
    protected Set getManagedInstanceSet(){
        return managedInstances;
    }
    
    /**
     * ���̃t�@�N�g�����񋟂���I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<p>
     *
     * @return ���̃t�@�N�g�����񋟂���I�u�W�F�N�g�̃C���X�^���X
     * @exception Exception �������ɗ�O�����������ꍇ
     */
    protected abstract Object createInstance() throws Exception;
    
    /**
     * ���̃t�@�N�g�����񋟂���I�u�W�F�N�g�̃C���X�^���X���e���v���[�g�p�ɐ�������B<p>
     *
     * @return ���̃t�@�N�g�����񋟂���I�u�W�F�N�g�̃C���X�^���X
     * @exception Exception �������ɗ�O�����������ꍇ
     */
    protected Object createTemplate() throws Exception{
        return createInstance();
    }
    
    /**
     * ���̃t�@�N�g���̐����㏈�����s���B<p>
     * {@link #createInstance()}���Ăяo���āA���̃t�@�N�g������������C���X�^���X�̐������\���ǂ������e�X�g����B<br>
     * 
     * @exception Exception createInstance()�Ɏ��s�����ꍇ
     */
    protected void postStartService() throws Exception{
        super.postStartService();
        if(isCreateTemplateOnStart){
            final Object obj = createTemplate();
            release(obj);
        }
    }
    
    /**
     * ���̃t�@�N�g���̔j���㏈�����s���B<p>
     * �Ǘ����Ă���C���X�^���X���N���A����B<br>
     * 
     * @exception Exception �j���㏈���Ɏ��s�����ꍇ
     */
    protected void postDestroyService() throws Exception{
        super.postDestroyService();
        managedInstances.clear();
    }
}
