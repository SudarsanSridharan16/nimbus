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
package jp.ossc.nimbus.service.writer.db;

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link DatabaseWriterService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DatabaseWriterService
 */
public interface DatabaseWriterServiceMBean
 extends ServiceBaseMBean, jp.ossc.nimbus.service.writer.MessageWriter{
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getConnectionFactoryServiceName();
    
    /**
     * INSERT����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O��ݒ肷��B<p>
     * �ȉ��̃t�H�[�}�b�g�Ŏw�肷��B<br>
     * INSERT����PreparedStatement�p��SQL=���ߍ��݃p�����[�^�̃L�[��,�c<br>
     * �E�ӂ̖��ߍ��݃p�����[�^�̃L�[���Ƃ́A{@link DatabaseWriterService#write(jp.ossc.nimbus.service.writer.WritableRecord)}���\�b�h�̓��͂œn�����{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}�Ɋi�[����Ă���{@link jp.ossc.nimbus.service.writer.WritableElement WritableElement}�̃L�[���ł���B<br>
     * <pre>
     * ��F
     *   insert into log_table(id, message) values(?, ?)=ID,MESSAGE
     * </pre>
     *
     * @param sqls INSERT����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O
     */
    public void setInsertSQL(Map sqls);
    
    /**
     * INSERT����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O���擾����B<p>
     *
     * @return INSERT����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O
     */
    public Map getInsertSQL();
    
    /**
     * UPDATE����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O��ݒ肷��B<p>
     * �ȉ��̃t�H�[�}�b�g�Ŏw�肷��B<br>
     * UPDATE����PreparedStatement�p��SQL=���ߍ��݃p�����[�^�̃L�[��,�c<br>
     * �E�ӂ̖��ߍ��݃p�����[�^�̃L�[���Ƃ́A{@link DatabaseWriterService#write(jp.ossc.nimbus.service.writer.WritableRecord)}���\�b�h�̓��͂œn�����{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}�Ɋi�[����Ă���{@link jp.ossc.nimbus.service.writer.WritableElement WritableElement}�̃L�[���ł���B<br>
     * <pre>
     * ��F
     *   update log_table set message=? where id=?=MESSAGE,ID
     * </pre>
     *
     * @param sqls UPDATE����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O
     */
    public void setUpdateSQL(Map sqls);
    
    /**
     * UPDATE����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O���擾����B<p>
     *
     * @return UPDATE����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O
     */
    public Map getUpdateSQL();
    
    /**
     * DELETE����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O��ݒ肷��B<p>
     * �ȉ��̃t�H�[�}�b�g�Ŏw�肷��B<br>
     * DELETE����PreparedStatement�p��SQL=���ߍ��݃p�����[�^�̃L�[��,�c<br>
     * �E�ӂ̖��ߍ��݃p�����[�^�̃L�[���Ƃ́A{@link DatabaseWriterService#write(jp.ossc.nimbus.service.writer.WritableRecord)}���\�b�h�̓��͂œn�����{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}�Ɋi�[����Ă���{@link jp.ossc.nimbus.service.writer.WritableElement WritableElement}�̃L�[���ł���B<br>
     * <pre>
     * ��F
     *   delete from log_table where id=?=ID
     * </pre>
     *
     * @param sqls DELETE����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O
     */
    public void setDeleteSQL(Map sqls);
    
    /**
     * DELETE����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O���擾����B<p>
     *
     * @return DELETE����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O
     */
    public Map getDeleteSQL();
    
    /**
     * SELECT����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O��ݒ肷��B<p>
     * �ȉ��̃t�H�[�}�b�g�Ŏw�肷��B<br>
     * SELECT����PreparedStatement�p��SQL=���ߍ��݃p�����[�^�̃L�[��,�c<br>
     * �E�ӂ̖��ߍ��݃p�����[�^�̃L�[���Ƃ́A{@link DatabaseWriterService#write(jp.ossc.nimbus.service.writer.WritableRecord)}���\�b�h�̓��͂œn�����{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}�Ɋi�[����Ă���{@link jp.ossc.nimbus.service.writer.WritableElement WritableElement}�̃L�[���ł���B<br>
     * <pre>
     * ��F
     *   select count(*) from log_table where id=?=ID
     * </pre>
     * �����Ŏw�肷��SELECT���̌��ʂ́A���R�[�h�����łȂ���΂Ȃ�Ȃ��B<br>
     * �܂��ASELECT�́AINSERT�ƁAUPDATE�܂���DELETE���ݒ肳��Ă���ꍇ�̂ݗL���ł���B<br>
     * SELECT��INSERT���ݒ肳��Ă���ꍇ�́ASELECT�̌��ʂ�0���̎�����INSERT�����B<br>
     * SELECT��UPDATE���ݒ肳��Ă���ꍇ�́ASELECT�̌��ʂ�0���łȂ�������UPDATE�����B<br>
     * SELECT��DELETE���ݒ肳��Ă���ꍇ�́ASELECT�̌��ʂ�0���łȂ�������DELETE�����B<br>
     * SELECT�ƁAINSERT�AUPDATE���ݒ肳��Ă���ꍇ�́ASELECT�̌��ʂ�0���̎���INSERT����A0���łȂ�����UPDATE�����B<br>
     * SELECT�ƁAINSERT�ADELETE���ݒ肳��Ă���ꍇ�́ASELECT�̌��ʂ�0���̎���INSERT����A0���łȂ�����DELETE�������INSERT�����B<br>
     *
     * @param sqls SELECT����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O
     */
    public void setSelectSQL(Map sqls);
    
    /**
     * SELECT����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O���擾����B<p>
     *
     * @return SELECT����PreparedStatement�p��SQL�Ɩ��ߍ��݃p�����[�^�̃L�[���Ƃ̃}�b�s���O
     */
    public Map getSelectSQL();
    
    /**
     * SQL���o�b�`���s����ۂ̃o�b�t�@�T�C�Y��ݒ肷��B<p>
     * �f�t�H���g�́A0�Ńo�b�`���s���Ȃ��B<br>
     * 0�ȏ�̒l���w�肷��ƁA���̃T�C�Y�������A{@link DatabaseWriterService#write(jp.ossc.nimbus.service.writer.WritableRecord)}���\�b�h�̌Ăяo�����e�𗭂ߍ��݁ASQL���o�b�`���s����B<br>
     * 
     * @param size �o�b�t�@�T�C�Y
     */
    public void setBufferSize(int size);
    
    /**
     * SQL���o�b�`���s����ۂ̃o�b�t�@�T�C�Y���擾����B<p>
     * 
     * @return �o�b�t�@�T�C�Y
     */
    public int getBufferSize();
    
    /**
     * SQL���o�b�`���s����ۂ̃^�C���A�E�g��ݒ肷��B<p>
     * �f�t�H���g�́A0�Ń^�C���A�E�g���Ȃ��B<br>
     * 0�ȏ�̒l���w�肷��ƁA{@link DatabaseWriterService#write(jp.ossc.nimbus.service.writer.WritableRecord)}���\�b�h���Ō�ɌĂяo���ꂽ��������w�肳�ꂽ���Ԃ��o�߂���ƁA���܂��Ă���o�b�`SQL�̐��Ɋւ�炸SQL���o�b�`���s����B<br>
     * �A���A{@link #setBufferSize(int)}��0�ȏ�̒l�i�o�b�`���s���L���ɂȂ�l�j���ݒ肳��Ă��Ȃ��ꍇ�́A���̐ݒ�͖����ł���B<br>
     * 
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setBufferTimeout(long timeout);
    
    /**
     * SQL���o�b�`���s����ۂ̃^�C���A�E�g���擾����B<p>
     * 
     * @return �^�C���A�E�g[ms]
     */
    public long getBufferTimeout();
    
    /**
     * �����R�~�b�g���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�����R�~�b�g����
     */
    public boolean isAutoCommit();
    
    /**
     * �����R�~�b�g���邩�ǂ�����ݒ肷��B<p>
     * �A���A{@link #setBufferSize(int)}�ɗL���Ȓl��ݒ肵�Ă���ꍇ�́A�ʃX���b�h�Ńo�b�`���s����邽�߁A���̑����Ɋւ�炸�A�����I�ɃR�~�b�g�����B<br>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isAuto �����R�~�b�g����ꍇ�Atrue
     */
    public void setAutoCommit(boolean isAuto);
}
