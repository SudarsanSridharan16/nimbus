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

package jp.ossc.nimbus.service.writer;

import jp.ossc.nimbus.core.*;

/**
 * �O���[�v�J�e�S���T�[�r�X�B<p>
 * �o�͐�𕪗ނ���J�e�S���T�[�r�X���O���[�s���O����J�e�S�������N���X�B<br>
 *
 * @author M.Takata
 */
public class GroupCategoryService extends ServiceBase
 implements GroupCategoryServiceMBean{
    
    private static final long serialVersionUID = 1415083627215180479L;
    
    /**
     * ���̃J�e�S�����L�����ǂ����̃t���O�B<p>
     * �L���ȏꍇ�Atrue
     */
    protected boolean isEnabled = true;
    
    /**
     * �O���[�s���O����J�e�S���̃T�[�r�X���z��B<p>
     */
    protected ServiceName[] categoryServiceNames;
    
    /**
     * �O���[�s���O����J�e�S���̃T�[�r�X�z��B<p>
     */
    protected Category[] categories;
    
    // GroupCategoryServiceMBean��JavaDoc
    public void setCategoryServiceNames(ServiceName[] names){
        categoryServiceNames = names;
    }
    
    // GroupCategoryServiceMBean��JavaDoc
    public ServiceName[] getCategoryServiceNames(){
        return categoryServiceNames;
    }
    
    /**
     * �O���[�s���O����J�e�S����ݒ肷��B<p>
     *
     * @param categories �O���[�s���O����J�e�S���̔z��
     */
    public void setCategories(Category[] categories) {
        this.categories = categories;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(categoryServiceNames != null){
            categories = new Category[categoryServiceNames.length];
            for(int i = 0, max = categoryServiceNames.length; i < max; i++){
                categories[i] = (Category)ServiceManagerFactory
                    .getServiceObject(categoryServiceNames[i]);
            }
        }
    }
    
    // Category��JavaDoc
    public boolean isEnabled(){
        return isEnabled;
    }
    
    // Category��JavaDoc
    public void setEnabled(boolean enable){
        isEnabled = enable;
    }
    
    // Category��JavaDoc
    public void write(Object elements) throws MessageWriteException{
        if(!isEnabled()){
            return;
        }
        if(categories != null){
            for(int i = 0, max = categories.length; i < max; i++){
                if(categories[i].isEnabled()){
                    categories[i].write(elements);
                }
            }
        }
    }
}
