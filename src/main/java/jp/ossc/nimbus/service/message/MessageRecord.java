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
package jp.ossc.nimbus.service.message;

import java.util.*;

/**
 * ���b�Z�[�W���R�[�h�B<p>
 *
 * @author H.Nakano
 */
public interface MessageRecord {
    
    /**
     * ���b�Z�[�WID���擾����B<p>
     *
     * @return ���b�Z�[�WID
     */
    public String getMessageCode();
    
    /**
     * �w�肵�����P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @return �w�肵�����P�[���̃��b�Z�[�W
     */
    public String makeMessage(Locale lc);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, Object embed);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, byte embed);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, short embed);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, char embed);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, int embed);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, long embed);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, float embed);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, double embed);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, boolean embed);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, Object[] embeds);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, byte[] embeds);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, short[] embeds);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, char[] embeds);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, int[] embeds);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, long[] embeds);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, float[] embeds);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, double[] embeds);
    
    /**
     * �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W���擾����B<p>
     * 
     * @param lc ���P�[��
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�肵�����P�[���̖��ߍ��݃��b�Z�[�W
     */
    public String makeMessage(Locale lc, boolean[] embeds);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage();
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embed ���ߍ��݃p�����[�^
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(Object embed);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embed ���ߍ��݃p�����[�^
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(byte embed);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embed ���ߍ��݃p�����[�^
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(short embed);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embed ���ߍ��݃p�����[�^
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(char embed);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embed ���ߍ��݃p�����[�^
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(int embed);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embed ���ߍ��݃p�����[�^
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(long embed);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embed ���ߍ��݃p�����[�^
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(float embed);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embed ���ߍ��݃p�����[�^
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(double embed);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embed ���ߍ��݃p�����[�^
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(boolean embed);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(Object[] embeds);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(byte[] embeds);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(short[] embeds);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(char[] embeds);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(int[] embeds);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(long[] embeds);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(float[] embeds);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(double[] embeds);
    
    /**
     * �f�t�H���g���P�[���̃��b�Z�[�W���擾����B<p>
     * 
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �f�t�H���g���P�[���̃��b�Z�[�W
     */
    public String makeMessage(boolean[] embeds);
    
    /**
     * �f�t�H���g���P�[���̃e���v���[�g���b�Z�[�W���擾����B<p>
     *
     * @return �f�t�H���g���P�[���̃e���v���[�g���b�Z�[�W
     */
    public String getMessageTemplate();
    
    /**
     * �w�肵�����P�[���̃e���v���[�g���b�Z�[�W���擾����B<p>
     *
     * @param lc ���P�[��
     * @return �w�肵�����P�[���̃e���v���[�g���b�Z�[�W
     */
    public String getMessageTemplate(Locale lc);
}
