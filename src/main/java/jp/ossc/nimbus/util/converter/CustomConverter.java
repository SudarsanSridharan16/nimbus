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
package jp.ossc.nimbus.util.converter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * �J�X�^���R���o�[�^�B<p>
 *
 * @author M.Takata
 */
public class CustomConverter
 implements Converter, StringConverter, CharacterConverter, StreamStringConverter, BindingStreamConverter,FormatConverter,
            java.io.Serializable, Cloneable{

    private static final long serialVersionUID = 727589924434574684L;

    protected List converters;

    /**
     * ��̃J�X�^���R���o�[�^�𐶐�����B<p>
     */
    public CustomConverter(){
    }

    /**
     * �J�X�^���R���o�[�^�𐶐�����B<p>
     *
     * @param convs �R���o�[�^�z��
     */
    public CustomConverter(Converter[] convs){
        if(convs != null && convs.length != 0){
            converters = new ArrayList();
            for(int i = 0; i < convs.length; i++){
                converters.add(convs[i]);
            }
        }
    }

    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     * �ǉ����ꂽ�R���o�[�^�̂����ŁA{@link ReversibleConverter}�̃C���X�^���X�ɕϊ���ʂ�ݒ肷��B<br>
     *
     * @param type �ϊ����
     */
    public void setConvertType(int type){
        if(converters != null){
            for(int i = 0, max = converters.size(); i < max; i++){
                if(converters.get(i) instanceof ReversibleConverter){
                    ((ReversibleConverter)converters.get(i)).setConvertType(type);
                }
            }
        }
    }

    /**
     * �I�u�W�F�N�g����X�g���[���֕ϊ�����ۂ̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �I�u�W�F�N�g����X�g���[���֕ϊ�����ۂ̕����G���R�[�f�B���O
     */
    public void setCharacterEncodingToStream(String encoding){
        if(converters != null){
            for(int i = 0, max = converters.size(); i < max; i++){
                if(converters.get(i) instanceof StreamStringConverter){
                    ((StreamStringConverter)converters.get(i)).setCharacterEncodingToStream(encoding);
                }
            }
        }
    }
    public String getCharacterEncodingToStream(){
        if(converters != null){
            for(int i = 0, max = converters.size(); i < max; i++){
                if(converters.get(i) instanceof StreamStringConverter){
                    return ((StreamStringConverter)converters.get(i)).getCharacterEncodingToStream();
                }
            }
        }
        return null;
    }

    /**
     * �X�g���[������I�u�W�F�N�g�֕ϊ�����ۂ̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �X�g���[������I�u�W�F�N�g�֕ϊ�����ۂ̕����G���R�[�f�B���O
     */
    public void setCharacterEncodingToObject(String encoding){
        if(converters != null){
            for(int i = 0, max = converters.size(); i < max; i++){
                if(converters.get(i) instanceof StreamStringConverter){
                    ((StreamStringConverter)converters.get(i)).setCharacterEncodingToObject(encoding);
                }
            }
        }
    }
    public String getCharacterEncodingToObject(){
        if(converters != null){
            for(int i = 0, max = converters.size(); i < max; i++){
                if(converters.get(i) instanceof StreamStringConverter){
                    return ((StreamStringConverter)converters.get(i)).getCharacterEncodingToObject();
                }
            }
        }
        return null;
    }

    public StreamStringConverter cloneCharacterEncodingToStream(String encoding){
        try{
            CustomConverter clone = (CustomConverter)clone();
            if(converters != null){
                clone.converters = new ArrayList();
                for(int i = 0, max = converters.size(); i < max; i++){
                    if(converters.get(i) instanceof StreamStringConverter){
                        clone.converters.add(((StreamStringConverter)converters.get(i)).cloneCharacterEncodingToStream(encoding));
                    }else{
                        clone.converters.add(converters.get(i));
                    }
                }
            }
            return clone;
        }catch(CloneNotSupportedException e){
            return null;
        }
    }

    public StreamStringConverter cloneCharacterEncodingToObject(String encoding){
        try{
            CustomConverter clone = (CustomConverter)clone();
            if(converters != null){
                clone.converters = new ArrayList();
                for(int i = 0, max = converters.size(); i < max; i++){
                    if(converters.get(i) instanceof StreamStringConverter){
                        clone.converters.add(((StreamStringConverter)converters.get(i)).cloneCharacterEncodingToObject(encoding));
                    }else{
                        clone.converters.add(converters.get(i));
                    }
                }
            }
            return clone;
        }catch(CloneNotSupportedException e){
            return null;
        }
    }

    public void setFormat(String format) {
        if(converters != null){
            for(int i = 0, max = converters.size(); i < max; i++){
                if(converters.get(i) instanceof FormatConverter){
                    ((FormatConverter)converters.get(i)).setFormat(format);
                }
            }
        }
    }

   /**
     * �R���o�[�^��ǉ�����B<p>
     *
     * @param converter �R���o�[�^
     * @return �������g�̃C���X�^���X
     */
    public Converter add(Converter converter){
        if(converters == null){
            converters = new ArrayList();
        }
        converters.add(converter);
        return this;
    }

    /**
     * �R���o�[�^���폜����B<p>
     *
     * @param converter �R���o�[�^
     */
    public void remove(Converter converter){
        if(converters == null){
            return;
        }
        converters.remove(converter);
    }

    /**
     * �R���o�[�^��S�č폜����B<p>
     */
    public void clear(){
        if(converters == null){
            return;
        }
        converters.clear();
    }

    /**
     * �w�肳�ꂽ�I�u�W�F�N�g��ϊ�����B<p>
     * �ǉ����ꂽ�R���o�[�^�ɏ����A�ϊ����˗����ĕϊ����ʂ�Ԃ��B<br>
     *
     * @param obj �ϊ��Ώۂ̃I�u�W�F�N�g
     * @return �ϊ���̃I�u�W�F�N�g
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convert(Object obj) throws ConvertException{
        Object tmp = obj;
        if(converters != null){
            for(int i = 0, max = converters.size(); i < max; i++){
                tmp = ((Converter)converters.get(i)).convert(tmp);
            }
        }
        return tmp;
    }

    /**
     * �w�肳�ꂽ�������ϊ�����B<p>
     * �ǉ����ꂽ�R���o�[�^�ɏ����A�ϊ����˗����ĕϊ����ʂ�Ԃ��B<br>
     *
     * @param str �ϊ��Ώۂ̃L�����N�^
     * @return �ϊ���̃L�����N�^
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public String convert(String str) throws ConvertException{
        Object tmp = str;
        if(converters != null){
            for(int i = 0, max = converters.size(); i < max; i++){
                tmp = ((Converter)converters.get(i)).convert(tmp);
            }
        }
        return (String)tmp;
    }

    /**
     * �w�肳�ꂽ�L�����N�^��ϊ�����B<p>
     * �ǉ����ꂽ�L�����N�^�R���o�[�^�ɏ����A�ϊ����˗����ĕϊ����ʂ�Ԃ��B<br>
     * {@link CharacterConverter}�ȊO��Converter�͖��������B
     *
     * @param c �ϊ��Ώۂ̃L�����N�^
     * @return �ϊ���̃L�����N�^
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public char convert(char c) throws ConvertException{
        char tmp = c;
        if(converters != null){
            for(int i = 0, max = converters.size(); i < max; i++){
                if(converters.get(i) instanceof CharacterConverter){
                    tmp = ((CharacterConverter)converters.get(i)).convert(tmp);
                }
            }
        }
        return tmp;
    }

    /**
     * �w�肳�ꂽ�L�����N�^��ϊ�����B<p>
     * �ǉ����ꂽ�R���o�[�^�ɏ����A�ϊ����˗����ĕϊ����ʂ�Ԃ��B<br>
     *
     * @param c �ϊ��Ώۂ̃L�����N�^
     * @return �ϊ���̃L�����N�^
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Character convert(Character c) throws ConvertException{
        Object tmp = c;
        if(converters != null){
            for(int i = 0, max = converters.size(); i < max; i++){
                tmp = ((Converter)converters.get(i)).convert(tmp);
            }
        }
        return (Character)tmp;
    }

    /**
     * �I�u�W�F�N�g����X�g���[���֕ϊ�����B<p>
     * �ǉ����ꂽ�R���o�[�^�ɏ����A�ϊ����˗����ĕϊ����ʂ�Ԃ��B
     * �A���A�Ō�̃R���o�[�^�́A{@link StreamConverter}�Ƃ݂Ȃ��āA{@link StreamConverter#convertToStream(Object)}���Ăяo���B<br>
     *
     * @param obj �I�u�W�F�N�g
     * @return �ϊ����ʂ�ǂݎ����̓X�g���[��
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public InputStream convertToStream(Object obj) throws ConvertException{
        Object tmp = obj;
        if(converters != null && converters.size() > 0){
            if(converters.size() > 1){
                for(int i = 0, max = converters.size() - 1; i < max; i++){
                    tmp = ((Converter)converters.get(i)).convert(tmp);
                }
            }
            tmp = ((StreamConverter)converters.get(converters.size() - 1)).convertToStream(tmp);
        }
        return (InputStream)tmp;
    }

    /**
     * �X�g���[������I�u�W�F�N�g�֕ϊ�����B<p>
     * �ǉ����ꂽ�R���o�[�^�ɏ����A�ϊ����˗����ĕϊ����ʂ�Ԃ��B
     * �A���A�ŏ��̃R���o�[�^�́A{@link StreamConverter}�Ƃ݂Ȃ��āA{@link StreamConverter#convertToObject(InputStream)}���Ăяo���B<br>
     *
     * @param is ���̓X�g���[��
     * @return �I�u�W�F�N�g
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream is) throws ConvertException{
        Object tmp = is;
        if(converters != null && converters.size() > 0){
            tmp = ((StreamConverter)converters.get(0)).convertToObject(is);
            if(converters.size() > 1){
                for(int i = 1, max = converters.size(); i < max; i++){
                    tmp = ((Converter)converters.get(i)).convert(tmp);
                }
            }
        }
        return tmp;
    }

    /**
     * �X�g���[������I�u�W�F�N�g�֕ϊ�����B<p>
     * �ǉ����ꂽ�R���o�[�^�ɏ����A�ϊ����˗����ĕϊ����ʂ�Ԃ��B
     * �A���A�ŏ��̃R���o�[�^�́A{@link StreamConverter}�A�܂���{@link BindingStreamConverter}�Ƃ݂Ȃ��āA{@link StreamConverter#convertToObject(InputStream)}�܂���{@link BindingStreamConverter#convertToObject(InputStream, Object)}���Ăяo���B<br>
     *
     * @param is ���̓X�g���[��
     * @param returnType �ϊ��Ώۂ̃I�u�W�F�N�g
     * @return �I�u�W�F�N�g
     * @throws ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream is, Object returnType) throws ConvertException{
        Object tmp = is;
        if(converters != null && converters.size() > 0){
            if(converters.get(0) instanceof BindingStreamConverter){
                tmp = ((BindingStreamConverter)converters.get(0)).convertToObject(is, returnType);
            }else{
                tmp = ((StreamConverter)converters.get(0)).convertToObject(is);
            }
            if(converters.size() > 1){
                for(int i = 1, max = converters.size(); i < max; i++){
                    if((converters.get(i) instanceof BindingStreamConverter)
                        && (tmp instanceof InputStream)){
                        tmp = ((BindingStreamConverter)converters.get(i)).convertToObject((InputStream)tmp, returnType);
                    }else{
                        tmp = ((Converter)converters.get(i)).convert(tmp);
                    }
                }
            }
        }
        return tmp;
    }

}
