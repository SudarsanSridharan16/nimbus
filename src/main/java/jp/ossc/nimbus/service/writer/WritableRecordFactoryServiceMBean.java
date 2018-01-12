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

import jp.ossc.nimbus.core.ServiceBaseMBean;
import java.util.*;

/**
 * {@link WritableRecordFactoryService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author Y.Tokuda
 */
public interface WritableRecordFactoryServiceMBean extends ServiceBaseMBean {
    
    /**
     * {@link WritableRecordFactory#createRecord(Object)}�Ŏw�肳�ꂽ�}�b�v�̃L�[�ɑ΂���WritableElement�����N���X�̃}�b�s���O���w�肷��B<p>
     * ���̃��\�b�h�Ŏw�肳��Ȃ������L�[�ɑ΂��ẮA{@link SimpleElement}���}�b�s���O�����B<br>
     * 
     * @param prop �L�[��WritableElement�����N���X�̃}�b�s���O
     */
    public void setImplementClasses(Properties prop);
    
    /**
     * {@link WritableRecordFactory#createRecord(Object)}�Ŏw�肳�ꂽ�}�b�v�̃L�[�ɑ΂���WritableElement�����N���X�̃}�b�s���O���擾����B<p>
     * 
     * @return �L�[��WritableElement�����N���X�̃}�b�s���O
     */
    public Properties getImplementClasses();
    
    /**
     * {@link WritableRecordFactory#createRecord(Object)}�Ŏw�肳�ꂽ�}�b�v�̃L�[�ɑ΂���WritableElement�����T�[�r�X�̃}�b�s���O���w�肷��B<p>
     * ��x��{@link WritableRecordFactory#createRecord(Object)}�ŁA����̃L�[��������o������ꍇ��A�}���`�X���b�h�ŌĂяo���ꍇ�́A����L�[�ɑ΂���WritableElement�C���X�^���X�́A���̓s�x���������K�v������̂ŁA{@link jp.ossc.nimbus.core.FactoryService FactoryService}�����������T�[�r�X���g�p���邩�Aservice�v�f��instance������factory���w�肷�邱�ƁB<br>
     * ���̃��\�b�h�Ŏw�肳��Ȃ������L�[�ɑ΂��ẮA{@link SimpleElement}���}�b�s���O�����B<br>
     * 
     * @param prop �L�[��WritableElement�����T�[�r�X�̃}�b�s���O
     */
    public void setImplementServiceNames(Properties prop);
    
    /**
     * {@link WritableRecordFactory#createRecord(Object)}�Ŏw�肳�ꂽ�}�b�v�̃L�[�ɑ΂���WritableElement�����T�[�r�X�̃}�b�s���O���擾����B<p>
     * 
     * @return �L�[��WritableElement�����T�[�r�X�̃}�b�s���O
     */
    public Properties getImplementServiceNames();
    
    /**
     * {@link WritableRecordFactory#createRecord(Object)}�Ŏw�肳�ꂽ�}�b�v�̒l�̃t�H�[�}�b�g��ݒ肷��B<p>
     * �t�H�[�}�b�g�ɂ́A{@link WritableRecordFactory#createRecord(Object)}�Ŏw�肳�ꂽ�}�b�v�̃L�[�ƁA�C�ӂ̕�������g�p�ł���B�L�[�́A"%"�ň͂ށB"%"���L�[�̃Z�p���[�^�ȊO�̕����Ƃ��Ďg�������ꍇ�́A"\"��O�ɕt���ăG�X�P�[�v����B"\"���G�X�P�[�v�����ȊO�Ƃ��Ďg�������ꍇ�́A"\"��2��d�˂�B<br>
     * <pre>
     *  ��F%DATE%,%MESSAGE%
     * </pre>
     *
     * @param fmt �t�H�[�}�b�g������
     */
    public void setFormat(String fmt);
    
    /**
     * {@link WritableRecordFactory#createRecord(Object)}�Ŏw�肳�ꂽ�}�b�v�̒l�̃t�H�[�}�b�g���擾����B<p>
     *
     * @return �t�H�[�}�b�g������
     */
    public String getFormat();
}
