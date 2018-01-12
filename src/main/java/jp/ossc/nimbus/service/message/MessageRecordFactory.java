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
 * ���b�Z�[�W���R�[�h�����C���^�[�t�F�C�X�B<p>
 *
 * @author H.Nakano
 */
public interface MessageRecordFactory {
    
    /**
     * �w�肵�����P�[���̃��b�Z�[�W���R�[�h��ǂݍ��ށB<p>
     * 
     * @param lo ���P�[��
     */
    public void findLocale(Locale lo);
    
    /**
     * ���b�Z�[�WID�̈ꗗ���擾����B<p>
     *
     * @return ���b�Z�[�WID�̈ꗗ
     */
    public String[] getMessageIds();
    
    /**
     * �w�肵�����b�Z�[�WID�̃��b�Z�[�W���R�[�h���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @return ���b�Z�[�W���R�[�h
     */
    public MessageRecord findMessageRecord(String messageId);
    
    /**
     * �w�肵�����b�Z�[�WID�̃e���v���[�g���b�Z�[�W���擾����B<p>
     * �e���v���[�g���b�Z�[�W�ɂ́A���ߍ��݃��b�Z�[�W�̃L�[���[�h�����̂܂܊܂܂��B<br>
     *
     * @param messageId ���b�Z�[�WID
     * @return �e���v���[�g���b�Z�[�W
     */
    public String findMessageTemplete(String messageId);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[���e���v���[�g���b�Z�[�W���擾����B<p>
     * �e���v���[�g���b�Z�[�W�ɂ́A���ߍ��݃��b�Z�[�W�̃L�[���[�h�����̂܂܊܂܂��B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @return �w�胍�P�[���e���v���[�g���b�Z�[�W
     */
    public String findMessageTemplete(Locale lo, String messageId);
    
    /**
     * �w�肵�����b�Z�[�WID�̃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @return ���b�Z�[�W
     */
    public String findMessage(String messageId);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @return �w�胍�P�[�����b�Z�[�W
     */
    public String findMessage(Locale lo, String messageId);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, Object[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, byte[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, short[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, char[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, int[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, long[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, float[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, double[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, boolean[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(
        Locale lo,
        String messageId,
        Object[] embeds
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, byte[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, short[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, char[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, int[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, long[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, float[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(
        Locale lo,
        String messageId,
        double[] embeds
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(
        Locale lo,
        String messageId,
        boolean[] embeds
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, Object embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, byte embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, short embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, char embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, int embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, long embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, float embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, double embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return ���ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(String messageId, boolean embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, Object embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, byte embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, short embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, char embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, int embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, long embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, float embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, double embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W���擾����B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @return �w�胍�P�[�����ߍ��݃��b�Z�[�W
     */
    public String findEmbedMessage(Locale lo, String messageId, boolean embed);
}
