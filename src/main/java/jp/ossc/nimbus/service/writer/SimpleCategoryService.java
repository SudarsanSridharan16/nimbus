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
 * �ȈՃJ�e�S���T�[�r�X�B<p>
 * �o�͐�𕪗ނ���J�e�S���T�[�r�X�̊ȈՎ����N���X�B<br>
 * �w�肳�ꂽ�o�͗v�f���A�ݒ肳�ꂽ{@link WritableRecordFactory}��{@link WritableRecord}�ɕϊ����āA�ݒ肳�ꂽ{@link MessageWriter}�ɏo�͂��˗�����B<br>
 *
 * @author M.Takata
 */
public class SimpleCategoryService extends ServiceBase
 implements SimpleCategoryServiceMBean{
    
    private static final long serialVersionUID = 1601430582489560068L;
    
    /**
     * ���̃J�e�S�����L�����ǂ����̃t���O�B<p>
     * �L���ȏꍇ�Atrue
     */
    protected boolean isEnabled = true;
    
    /**
     * ���̃J�e�S���̏o�͐�ƂȂ�MessageWriter�̃T�[�r�X���B<p>
     */
    protected ServiceName writerName;
    
    /**
     * ���̃J�e�S���̏o�͐�ƂȂ�MessageWriter�I�u�W�F�N�g�B<p>
     */
    protected MessageWriter writer;
    
    /**
     * ���̃J�e�S���̏o�̓t�H�[�}�b�g�����߂�WritableRecordFactory�̃T�[�r�X���B<p>
     */
    protected ServiceName recordFactoryName;
    
    /**
     * ���̃J�e�S���̏o�̓t�H�[�}�b�g�����߂�WritableRecordFactory�I�u�W�F�N�g�B<p>
     */
    protected WritableRecordFactory recordFactory;
    
    // SimpleCategoryServiceMBean��JavaDoc
    public void setMessageWriterServiceName(ServiceName name){
        writerName = name;
    }
    
    // SimpleCategoryServiceMBean��JavaDoc
    public ServiceName getMessageWriterServiceName(){
        return writerName;
    }
    
    // SimpleCategoryServiceMBean��JavaDoc
    public void setWritableRecordFactoryServiceName(ServiceName name){
        recordFactoryName = name;
    }
    
    // SimpleCategoryServiceMBean��JavaDoc
    public ServiceName getWritableRecordFactoryServiceName(){
        return recordFactoryName;
    }
    
    /**
     * ���̃J�e�S���̏o�͂��s��MessageWriter�T�[�r�X��ݒ肷��B<p>
     *
     * @param writer ���̃J�e�S���̏o�͂��s��MessageWriter�T�[�r�X
     */
    public void setMessageWriterService(MessageWriter writer){
        this.writer = writer;
    }
    
    /**
     * ���̃J�e�S���̏o�͂��s��MessageWriter�T�[�r�X���擾����B<p>
     *
     * @return ���̃J�e�S���̏o�͂��s��MessageWriter�T�[�r�X
     */
    public MessageWriter getMessageWriterService(){
        return this.writer;
    }
    
    /**
     * ���̃J�e�S���̏o�̓t�H�[�}�b�g�����߂�WritableRecordFactory�T�[�r�X��ݒ肷��B<p>
     *
     * @param factory ���̃J�e�S���̏o�̓t�H�[�}�b�g�����߂�WritableRecordFactory�T�[�r�X
     */
    public void setWritableRecordFactoryService(WritableRecordFactory factory){
        recordFactory = factory;
    }
    
    /**
     * ���̃J�e�S���̏o�̓t�H�[�}�b�g�����߂�WritableRecordFactory�T�[�r�X���擾����B<p>
     *
     * @return ���̃J�e�S���̏o�̓t�H�[�}�b�g�����߂�WritableRecordFactory�T�[�r�X
     */
    public WritableRecordFactory getWritableRecordFactoryService(){
        return recordFactory;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(writerName != null){
            writer = (MessageWriter)ServiceManagerFactory
                .getServiceObject(writerName);
        }
        if(recordFactoryName != null){
            recordFactory = (WritableRecordFactory)ServiceManagerFactory
                .getServiceObject(recordFactoryName);
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
        writer.write(recordFactory.createRecord(elements));
    }
}
