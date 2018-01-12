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

import java.util.*;
import java.beans.*;
import java.io.*;

/**
 * {@link Properties}�^��PropertyEditor�N���X�B<p>
 * �v���p�e�B�t�@�C���`���̕������java.util.Properties�^�̃I�u�W�F�N�g�ɕϊ�����B<br>
 * ��{�I�ɂ́A�v���p�e�B�t�@�C���̎d�l�ɏ]���B�قȂ�̂́A�e�s�̑O��̋󔒂��g���������B�󔒂́Ajava.lang.Character#isWhitespace(char)�Ŕ��肳���B�܂��A"&lt;!--"��"--&gt;"�Ɉ͂܂ꂽ������̓R�����g�Ɖ��߂��ꖳ�������B�܂��A"${"��"}"�Ɉ͂܂ꂽ������́A�����̃V�X�e���v���p�e�B�ƒu�������B<br>
 * <p>
 * ��F<br>
 * &nbsp;&nbsp;A=a<br>
 * &nbsp;&nbsp;B=b<br>
 * &nbsp;&nbsp;C=c<br>
 * &nbsp;&nbsp;D=d<br>
 * &nbsp;&nbsp;&lt;!--E=e<br>
 * &nbsp;&nbsp;F=f--&gt;<br>
 * <br>
 * �̂悤�ȕ�����<br>
 * <br>
 * &nbsp;&nbsp;A=a<br>
 * &nbsp;&nbsp;B=b<br>
 * &nbsp;&nbsp;C=c<br>
 * &nbsp;&nbsp;D=d<br>
 * <br>
 * �Ə����ꂽ�v���p�e�B�t�@�C���Ɠ��l��Properties�I�u�W�F�N�g�ɕϊ������B<br>
 *
 * @author M.Takata
 */
public class PropertiesEditor extends PropertyEditorSupport
 implements java.io.Serializable{
    
    private static final long serialVersionUID = -8656653312703767785L;
    
    private static final String EMPTY = "";
    
    /**
     * �w�肳�ꂽ���������͂��ăv���p�e�B�l��ݒ肷��B<p>
     *
     * @param text ��͂���镶����
     */
    public void setAsText(String text){
        if(text == null){
            setValue(null);
            return;
        }
        String tmpText = Utility.replaceSystemProperty(Utility.xmlComentOut(text));
        final int length = tmpText.length();
        if(tmpText == null || length == 0){
            setValue(new Properties());
            return;
        }
        if(tmpText.indexOf("\\u") != -1){
            tmpText = Utility.unicodeConvert(tmpText);
        }
        final StringReader sr = new StringReader(tmpText);
        final BufferedReader br = new BufferedReader(sr, length);
        final Properties result = new Properties();
        try{
            String line = null;
            while((line = br.readLine()) != null){
                line = Utility.trim(line);
                final int index = line.indexOf('=');
                if(index == -1){
                    continue;
                }
                final String name = line.substring(0, index);
                String value = null;
                if(index == line.length() - 1){
                    value = EMPTY;
                }else{
                    value = line.substring(index + 1);
                }
                result.setProperty(name, value);
            }
        }catch(IOException e){
            // �N���Ȃ��͂�
            e.printStackTrace();
        }finally{
            try{
                br.close();
            }catch(IOException e){
                // �N���Ȃ��͂�
                e.printStackTrace();
            }
            sr.close();
        }
        setValue(result);
    }
    
    /**
     * �v���p�e�B��������擾����B<p>
     *
     * @return �v���p�e�B������
     */
    public String getAsText(){
        final Properties prop = (Properties)getValue();
        if(prop == null){
            return null;
        }
        final StringWriter sw = new StringWriter();
        final PrintWriter writer = new PrintWriter(sw);
        final Enumeration names = prop.propertyNames();
        while(names.hasMoreElements()){
            final String name = (String)names.nextElement();
            writer.print(name);
            writer.print('=');
            writer.print(prop.getProperty(name));
            if(names.hasMoreElements()){
                writer.println();
            }
        }
        return sw.toString();
    }
}
