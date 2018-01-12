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
package jp.ossc.nimbus.beans;

import java.util.Date;


/**
 * {@link java.sql.Date}�^��PropertyEditor�N���X�B<p>
 * ���t������iyyyy/MM/dd HH:mm:ss SSS�j��java.sql.Date�^�̃I�u�W�F�N�g�ɕϊ�����B<br>
 * "${"��"}"�Ɉ͂܂ꂽ������́A�����̃V�X�e���v���p�e�B�ƒu�������B<br>
 * <p>
 * ��F<br>
 * &nbsp;&nbsp;2006/08/15 15:20:11 100<br>
 * <br>
 * �̂悤�ȕ�����<br>
 * <br>
 * &nbsp;&nbsp;new java.sql.Date(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS").parse("2006/08/15 15:20:11 100").getTime())<br>
 * <br>
 * �̂悤�ɕϊ������B<br>
 * �܂��A�ݒ肷��K�v�̂Ȃ��t�B�[���h�͋�ɂ���ƁA���̃t�B�[���h�̍ŏ��l�ɐݒ肳���B<br>
 * ��F<br>
 * &nbsp;&nbsp;//15 15::11<br>
 * <br>
 * �̂悤�ȕ�����<br>
 * <br>
 * &nbsp;&nbsp;new java.sql.Date(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS").parse("1970/01/15 15:00:11 000").getTime())<br>
 * <br>
 * �̂悤�ɕϊ������B<br>
 * �܂��A���ݎ�������ݒ肵�����ꍇ�́A�e�t�B�[���h��"NOW"��ݒ肷��B<br>
 * ��F<br>
 * &nbsp;&nbsp;NOW/NOW/15 15:NOW:11 NOW<br>
 * <br>
 * �̂悤�ȕ����񂪁A���ݓ��t��2006/09/01 13:59:40 150�Ƃ����<br>
 * <br>
 * &nbsp;&nbsp;new java.sql.Date(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS").parse("2006/09/15 15:59:11 150").getTime())<br>
 * <br>
 * �̂悤�ɕϊ������B<br>
 * �܂��A�P���Ɍ��ݎ�����ݒ肵�����ꍇ�́A"NOW"��ݒ肷��B<br>
 * ��F<br>
 * &nbsp;&nbsp;NOW<br>
 * <br>
 * �̂悤�ȕ����񂪁A<br>
 * <br>
 * &nbsp;&nbsp;new java.sql.Date(System.currentTimeMillis())<br>
 * <br>
 * �̂悤�ɕϊ������B<br>
 *
 * @author M.Takata
 */
public class SQLDateEditor extends DateEditor
 implements java.io.Serializable{
    
    private static final long serialVersionUID = 4216221057481182615L;

    /**
     * �w�肳�ꂽ���������͂��ăv���p�e�B�l��ݒ肷��B<p>
     *
     * @param text ��͂���镶����
     */
    public void setAsText(String text){
        super.setAsText(text);
        Date date = (Date)super.getValue();
        if(date != null){
            super.setValue(new java.sql.Date(date.getTime()));
        }
    }
}
