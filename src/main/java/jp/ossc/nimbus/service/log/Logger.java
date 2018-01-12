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
package jp.ossc.nimbus.service.log;

import jp.ossc.nimbus.lang.*;
import java.util.*;

/**
 * ���O�C���^�[�t�F�C�X�B<p>
 * 
 * @author Y.Tokuda
 */
public interface Logger {
    
    /**
     * �w�肵�����b�Z�[�WID�̃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     */
    public void write(String messageId);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(String messageId, Object embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(String messageId, byte embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(String messageId, short embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(String messageId, char embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(String messageId, int embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(String messageId, long embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(String messageId, float embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(String messageId, double embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(String messageId, boolean embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(String messageId, Object[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(String messageId, byte[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(String messageId, short[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(String messageId, char[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(String messageId, int[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(String messageId, long[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(String messageId, float[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(String messageId, double[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̖��ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(String messageId, boolean[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param oException ��O
     */
    public void write(String messageId, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(String messageId, Object embed, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(String messageId, byte embed, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(String messageId, short embed, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(String messageId, char embed, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(String messageId, int embed, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(String messageId, long embed, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(String messageId, float embed, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(String messageId, double embed, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(String messageId, boolean embed, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(String messageId, Object[] embeds, Throwable oException) ;
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(String messageId, byte[] embeds, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(String messageId, short[] embeds, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(String messageId, char[] embeds, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(String messageId, int[] embeds, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(String messageId, long[] embeds, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(String messageId, float[] embeds, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(String messageId, double[] embeds, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(String messageId, boolean[] embeds, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     */
    public void write(Locale lo, String messageId);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(Locale lo, String messageId, Object embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(Locale lo, String messageId, byte embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(Locale lo, String messageId, short embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(Locale lo, String messageId, char embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(Locale lo, String messageId, int embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(Locale lo, String messageId, long embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(Locale lo, String messageId, float embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(Locale lo, String messageId, double embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     */
    public void write(Locale lo, String messageId, boolean embed);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(Locale lo, String messageId, Object[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(Locale lo, String messageId, byte[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(Locale lo, String messageId, short[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(Locale lo, String messageId, char[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(Locale lo, String messageId, int[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(Locale lo, String messageId, long[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(Locale lo, String messageId, float[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(Locale lo, String messageId, double[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̎w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     * �w�肵�����P�[���̃��b�Z�[�W��������Ȃ��ꍇ�ɂ́A���߂����P�[���̃��b�Z�[�W���o�͂���B<br>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     */
    public void write(Locale lo, String messageId, boolean[] embeds);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param oException ��O
     */
    public void write(Locale lo, String messageId, Throwable oException);
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        Object embed,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        byte embed,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        short embed,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        char embed,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        int embed,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        long embed,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        float embed,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        double embed,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embed ���ߍ��݃p�����[�^
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        boolean embed,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        Object[] embeds,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        byte[] embeds,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        short[] embeds,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        char[] embeds,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        int[] embeds,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        long[] embeds,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        float[] embeds,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        double[] embeds,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�̗�O�t���w�胍�P�[�����ߍ��݃��b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param messageId ���b�Z�[�WID
     * @param embeds ���ߍ��݃p�����[�^�z��
     * @param oException ��O
     */
    public void write(
        Locale lo,
        String messageId,
        boolean[] embeds,
        Throwable oException
    );
    
    /**
     * �w�肵�����b�Z�[�WID�t����O�̗�O�t�����b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param e ���b�Z�[�WID�t����O
     */
    public void write(AppException e);
    
    /**
     * �w�肵�����b�Z�[�WID�t����O�̗�O�t���w�胍�P�[�����b�Z�[�W�����O�ɏo�͂���B<p>
     *
     * @param lo ���P�[��
     * @param e ���b�Z�[�WID�t����O
     */
    public void write(Locale lo, AppException e);
    
    /**
     * �w�肳�ꂽ���b�Z�[�WID�̃��b�Z�[�W�����O�o�͂���邩�ǂ����𔻒肷��B<p>
     *
     * @param messageId ���b�Z�[�WID
     * @return �w�肳�ꂽ���b�Z�[�WID�̃��b�Z�[�W�����O�o�͂����ꍇtrue
     */
    public boolean isWrite(String messageId);
    
    /**
     * �w�肵�����b�Z�[�W�̃f�o�b�O���O���o�͂���B<p>
     *
     * @param msg ���b�Z�[�W
     */
    public void debug(Object msg);
    
    /**
     * �w�肵�����b�Z�[�W�̗�O�t���f�o�b�O���O���o�͂���B<p>
     *
     * @param msg ���b�Z�[�W
     * @param oException ��O
     */
    public void debug(Object msg, Throwable oException);
    
    /**
     * �f�o�b�O���O���o�͂���邩�ǂ����𔻒肷��B<p>
     *
     * @return �f�o�b�O���O���o�͂����ꍇtrue
     */
    public boolean isDebugWrite();
}
