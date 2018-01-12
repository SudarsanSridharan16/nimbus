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

import java.io.*;

/**
 * �R���o�[�^���[�e�B���e�B�B<p>
 * 
 * @author M.Takata
 */
public class Converters{
    
    private static final long serialVersionUID = -7008480092627389111L;
    
    private static final StringConverter ALPHABET_HANKAKU_TO_ZENKAKU_STRING
         = new StringConverterImpl(
               new AlphabetStringConverter(
                   HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
               )
           );
    private static final StringConverter ALPHABET_ZENKAKU_TO_HANKAKU_STRING
         = new StringConverterImpl(
               new AlphabetStringConverter(
                   HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
               )
           );
    private static final StringConverter KATAKANA_HANKAKU_TO_ZENKAKU_STRING
         = new StringConverterImpl(
               new KatakanaStringConverter(
                   HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
               )
           );
    private static final StringConverter KATAKANA_ZENKAKU_TO_HANKAKU_STRING
         = new StringConverterImpl(
               new KatakanaStringConverter(
                   HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
               )
           );
    private static final StringConverter NUMBER_HANKAKU_TO_ZENKAKU_STRING
         = new StringConverterImpl(
               new NumberStringConverter(
                   HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
               )
           );
    private static final StringConverter NUMBER_ZENKAKU_TO_HANKAKU_STRING
         = new StringConverterImpl(
               new NumberStringConverter(
                   HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
               )
           );
    private static final StringConverter SYMBOL_HANKAKU_TO_ZENKAKU_STRING
         = new StringConverterImpl(
               new SymbolStringConverter(
                   HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
               )
           );
    private static final StringConverter SYMBOL_ZENKAKU_TO_HANKAKU_STRING
         = new StringConverterImpl(
               new SymbolStringConverter(
                   HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
               )
           );
    private static final StringConverter KATAKANA_TO_HIRAGANA_STRING
         = new StringConverterImpl(
               new KatakanaHiraganaStringConverter(
                   KatakanaHiraganaStringConverter.KATAKANA_TO_HIRAGANA
               )
           );
    private static final StringConverter HIRAGANA_TO_KATAKANA_STRING
         = new StringConverterImpl(
               new KatakanaHiraganaStringConverter(
                   KatakanaHiraganaStringConverter.HIRAGANA_TO_KATAKANA
               )
           );
    private static final StringConverter HANKAKU_TO_ZENKAKU_STRING
         = new StringConverterImpl(
               new CustomConverter(
                   new Converter[]{
                       new AlphabetStringConverter(
                           HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
                       ),
                       new KatakanaStringConverter(
                           HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
                       ),
                       new NumberStringConverter(
                           HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
                       ),
                       new SymbolStringConverter(
                           HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
                       )
                   }
               )
           );
    private static final StringConverter ZENKAKU_TO_HANKAKU_STRING
         = new StringConverterImpl(
               new CustomConverter(
                   new Converter[]{
                       new AlphabetStringConverter(
                           HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
                       ),
                       new KatakanaStringConverter(
                           HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
                       ),
                       new NumberStringConverter(
                           HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
                       ),
                       new SymbolStringConverter(
                           HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
                       )
                   }
               )
           );
    
    private static final CharacterConverter ALPHABET_HANKAKU_TO_ZENKAKU_CHAR
         = new CharacterConverterImpl(
               new AlphabetCharacterConverter(
                   HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
               )
           );
    private static final CharacterConverter ALPHABET_ZENKAKU_TO_HANKAKU_CHAR
         = new CharacterConverterImpl(
               new AlphabetCharacterConverter(
                   HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
               )
           );
    private static final CharacterConverter KATAKANA_HANKAKU_TO_ZENKAKU_CHAR
         = new CharacterConverterImpl(
               new KatakanaCharacterConverter(
                   HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
               )
           );
    private static final CharacterConverter KATAKANA_ZENKAKU_TO_HANKAKU_CHAR
         = new CharacterConverterImpl(
               new KatakanaCharacterConverter(
                   HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
               )
           );
    private static final CharacterConverter NUMBER_HANKAKU_TO_ZENKAKU_CHAR
         = new CharacterConverterImpl(
               new NumberCharacterConverter(
                   HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
               )
           );
    private static final CharacterConverter NUMBER_ZENKAKU_TO_HANKAKU_CHAR
         = new CharacterConverterImpl(
               new NumberCharacterConverter(
                   HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
               )
           );
    private static final CharacterConverter SYMBOL_HANKAKU_TO_ZENKAKU_CHAR
         = new CharacterConverterImpl(
               new SymbolCharacterConverter(
                   HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
               )
           );
    private static final CharacterConverter SYMBOL_ZENKAKU_TO_HANKAKU_CHAR
         = new CharacterConverterImpl(
               new SymbolCharacterConverter(
                   HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
               )
           );
    private static final CharacterConverter KATAKANA_TO_HIRAGANA_CHAR
         = new CharacterConverterImpl(
               new KatakanaHiraganaCharacterConverter(
                   KatakanaHiraganaCharacterConverter.KATAKANA_TO_HIRAGANA
               )
           );
    private static final CharacterConverter HIRAGANA_TO_KATAKANA_CHAR
         = new CharacterConverterImpl(
               new KatakanaHiraganaCharacterConverter(
                   KatakanaHiraganaCharacterConverter.HIRAGANA_TO_KATAKANA
               )
           );
    private static final CharacterConverter HANKAKU_TO_ZENKAKU_CHAR
         = new CharacterConverterImpl(
               new CustomConverter(
                   new Converter[]{
                       new AlphabetCharacterConverter(
                           HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
                       ),
                       new KatakanaCharacterConverter(
                           HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
                       ),
                       new NumberCharacterConverter(
                           HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
                       ),
                       new SymbolCharacterConverter(
                           HankakuZenkakuConverter.HANKAKU_TO_ZENKAKU
                       )
                   }
               )
           );
    private static final CharacterConverter ZENKAKU_TO_HANKAKU_CHAR
         = new CharacterConverterImpl(
              new CustomConverter(
                  new Converter[]{
                      new AlphabetCharacterConverter(
                          HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
                      ),
                      new KatakanaCharacterConverter(
                          HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
                      ),
                      new NumberCharacterConverter(
                          HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
                      ),
                       new SymbolCharacterConverter(
                          HankakuZenkakuConverter.ZENKAKU_TO_HANKAKU
                      )
                  }
              )
         );
    
    private Converters(){
    }
    
    /**
     * ���p�p�����S�p�p���R���o�[�^���擾����B<p>
     *
     * @return ���p�p�����S�p�p���R���o�[�^
     * @see AlphabetStringConverter
     */
    public static StringConverter getAlphabetHankakuFromZenkakuStringConverter(){
        return ALPHABET_HANKAKU_TO_ZENKAKU_STRING;
    }
    
    /**
     * �S�p�p�������p�p���R���o�[�^���擾����B<p>
     *
     * @return �S�p�p�������p�p���R���o�[�^
     * @see AlphabetStringConverter
     */
    public static StringConverter getAlphabetZenkakuFromHankakuStringConverter(){
        return ALPHABET_ZENKAKU_TO_HANKAKU_STRING;
    }
    
    /**
     * ���p�J�^�J�i���S�p�J�^�J�i�R���o�[�^���擾����B<p>
     *
     * @return ���p�J�^�J�i���S�p�J�^�J�i�R���o�[�^
     * @see KatakanaStringConverter
     */
    public static StringConverter getKatakanaHankakuFromZenkakuStringConverter(){
        return KATAKANA_HANKAKU_TO_ZENKAKU_STRING;
    }
    
    /**
     * �S�p�J�^�J�i�����p�J�^�J�i�R���o�[�^���擾����B<p>
     *
     * @return �S�p�J�^�J�i�����p�J�^�J�i�R���o�[�^
     * @see KatakanaStringConverter
     */
    public static StringConverter getKatakanaZenkakuFromHankakuStringConverter(){
        return KATAKANA_ZENKAKU_TO_HANKAKU_STRING;
    }
    
    /**
     * ���p�������S�p�����R���o�[�^���擾����B<p>
     *
     * @return ���p�������S�p�����R���o�[�^
     * @see NumberStringConverter
     */
    public static StringConverter getNumberHankakuFromZenkakuStringConverter(){
        return NUMBER_HANKAKU_TO_ZENKAKU_STRING;
    }
    
    /**
     * �S�p���������p�����R���o�[�^���擾����B<p>
     *
     * @return �S�p���������p�����R���o�[�^
     * @see NumberStringConverter
     */
    public static StringConverter getNumberZenkakuFromHankakuStringConverter(){
        return NUMBER_ZENKAKU_TO_HANKAKU_STRING;
    }
    
    /**
     * ���p�L�����S�p�L���R���o�[�^���擾����B<p>
     *
     * @return ���p�L�����S�p�L���R���o�[�^
     * @see SymbolStringConverter
     */
    public static StringConverter getSymbolHankakuFromZenkakuStringConverter(){
        return SYMBOL_HANKAKU_TO_ZENKAKU_STRING;
    }
    
    /**
     * �S�p�L�������p�L���R���o�[�^���擾����B<p>
     *
     * @return �S�p�L�������p�L���R���o�[�^
     * @see SymbolStringConverter
     */
    public static StringConverter getSymbolZenkakuFromHankakuStringConverter(){
        return SYMBOL_ZENKAKU_TO_HANKAKU_STRING;
    }
    
    /**
     * �S�p�J�^�J�i���S�p�Ђ炪�ȃR���o�[�^���擾����B<p>
     *
     * @return �S�p�J�^�J�i���S�p�Ђ炪�ȃR���o�[�^
     * @see KatakanaHiraganaStringConverter
     */
    public static StringConverter getKatakanaFromHiraganaStringConverter(){
        return KATAKANA_TO_HIRAGANA_STRING;
    }
    
    /**
     * �S�p�Ђ炪�ȁ��S�p�J�^�J�i�R���o�[�^���擾����B<p>
     *
     * @return �S�p�Ђ炪�ȁ��S�p�J�^�J�i�R���o�[�^
     * @see KatakanaHiraganaStringConverter
     */
    public static StringConverter getHiraganaFromKatakanaStringConverter(){
        return HIRAGANA_TO_KATAKANA_STRING;
    }
    
    /**
     * ���p���S�p�R���o�[�^���擾����B<p>
     * �p���A�J�^�J�i�A�����A�L���̔��p���S�p�ϊ����s���R���o�[�^���擾����B
     *
     * @return ���p���S�p�R���o�[�^
     * @see AlphabetStringConverter
     * @see KatakanaStringConverter
     * @see NumberStringConverter
     * @see SymbolStringConverter
     */
    public static StringConverter getHankakuFromZenkakuStringConverter(){
        return HANKAKU_TO_ZENKAKU_STRING;
    }
    
    /**
     * �S�p�����p�R���o�[�^���擾����B<p>
     * �p���A�J�^�J�i�A�����A�L���̑S�p�����p�ϊ����s���R���o�[�^���擾����B
     *
     * @return �S�p�����p�R���o�[�^
     * @see AlphabetStringConverter
     * @see KatakanaStringConverter
     * @see NumberStringConverter
     * @see SymbolStringConverter
     */
    public static StringConverter getZenkakuFromHankakuStringConverter(){
        return ZENKAKU_TO_HANKAKU_STRING;
    }
    
    /**
     * ���p�p�����S�p�p���R���o�[�^���擾����B<p>
     *
     * @return ���p�p�����S�p�p���R���o�[�^
     * @see AlphabetCharacterConverter
     */
    public static CharacterConverter getAlphabetHankakuFromZenkakuCharacterConverter(){
        return ALPHABET_HANKAKU_TO_ZENKAKU_CHAR;
    }
    /**
     * �S�p�p�������p�p���R���o�[�^���擾����B<p>
     *
     * @return �S�p�p�������p�p���R���o�[�^
     * @see AlphabetCharacterConverter
     */
    public static CharacterConverter getAlphabetZenkakuFromHankakuCharacterConverter(){
        return ALPHABET_ZENKAKU_TO_HANKAKU_CHAR;
    }
    /**
     * ���p�J�^�J�i���S�p�J�^�J�i�R���o�[�^���擾����B<p>
     *
     * @return ���p�J�^�J�i���S�p�J�^�J�i�R���o�[�^
     * @see KatakanaCharacterConverter
     */
    public static CharacterConverter getKatakanaHankakuFromZenkakuCharacterConverter(){
        return KATAKANA_HANKAKU_TO_ZENKAKU_CHAR;
    }
    /**
     * �S�p�J�^�J�i�����p�J�^�J�i�R���o�[�^���擾����B<p>
     *
     * @return �S�p�J�^�J�i�����p�J�^�J�i�R���o�[�^
     * @see KatakanaCharacterConverter
     */
    public static CharacterConverter getKatakanaZenkakuFromHankakuCharacterConverter(){
        return KATAKANA_ZENKAKU_TO_HANKAKU_CHAR;
    }
    /**
     * ���p�������S�p�����R���o�[�^���擾����B<p>
     *
     * @return ���p�������S�p�����R���o�[�^
     * @see NumberCharacterConverter
     */
    public static CharacterConverter getNumberHankakuFromZenkakuCharacterConverter(){
        return NUMBER_HANKAKU_TO_ZENKAKU_CHAR;
    }
    /**
     * �S�p���������p�����R���o�[�^���擾����B<p>
     *
     * @return �S�p���������p�����R���o�[�^
     * @see NumberCharacterConverter
     */
    public static CharacterConverter getNumberZenkakuFromHankakuCharacterConverter(){
        return NUMBER_ZENKAKU_TO_HANKAKU_CHAR;
    }
    /**
     * ���p�L�����S�p�L���R���o�[�^���擾����B<p>
     *
     * @return ���p�L�����S�p�L���R���o�[�^
     * @see SymbolCharacterConverter
     */
    public static CharacterConverter getSymbolHankakuFromZenkakuCharacterConverter(){
        return SYMBOL_HANKAKU_TO_ZENKAKU_CHAR;
    }
    /**
     * �S�p�L�������p�L���R���o�[�^���擾����B<p>
     *
     * @return �S�p�L�������p�L���R���o�[�^
     * @see SymbolCharacterConverter
     */
    public static CharacterConverter getSymbolZenkakuFromHankakuCharacterConverter(){
        return SYMBOL_ZENKAKU_TO_HANKAKU_CHAR;
    }
    /**
     * �S�p�J�^�J�i���S�p�Ђ炪�ȃR���o�[�^���擾����B<p>
     *
     * @return �S�p�J�^�J�i���S�p�Ђ炪�ȃR���o�[�^
     * @see KatakanaHiraganaCharacterConverter
     */
    public static CharacterConverter getKatakanaFromHiraganaCharacterConverter(){
        return KATAKANA_TO_HIRAGANA_CHAR;
    }
    /**
     * �S�p�Ђ炪�ȁ��S�p�J�^�J�i�R���o�[�^���擾����B<p>
     *
     * @return �S�p�Ђ炪�ȁ��S�p�J�^�J�i�R���o�[�^
     * @see KatakanaHiraganaCharacterConverter
     */
    public static CharacterConverter getHiraganaFromKatakanaCharacterConverter(){
        return HIRAGANA_TO_KATAKANA_CHAR;
    }
    /**
     * ���p���S�p�R���o�[�^���擾����B<p>
     * �p���A�J�^�J�i�A�����A�L���̔��p���S�p�ϊ����s���R���o�[�^���擾����B
     *
     * @return ���p���S�p�R���o�[�^
     * @see AlphabetCharacterConverter
     * @see KatakanaCharacterConverter
     * @see NumberCharacterConverter
     * @see SymbolCharacterConverter
     */
    public static CharacterConverter getHankakuFromZenkakuCharacterConverter(){
        return HANKAKU_TO_ZENKAKU_CHAR;
    }
    /**
     * �S�p�����p�R���o�[�^���擾����B<p>
     * �p���A�J�^�J�i�A�����A�L���̑S�p�����p�ϊ����s���R���o�[�^���擾����B
     *
     * @return �S�p�����p�R���o�[�^
     * @see AlphabetCharacterConverter
     * @see KatakanaCharacterConverter
     * @see NumberCharacterConverter
     * @see SymbolCharacterConverter
     */
    public static CharacterConverter getZenkakuFromHankakuCharacterConverter(){
        return ZENKAKU_TO_HANKAKU_CHAR;
    }
    
    /**
     * �J�X�^��������R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @param fromStrs �ϊ��㕶����z��
     * @param toStrs �ϊ��Ώە�����z��
     * @return �J�X�^��������R���o�[�^
     * @see ReversibleConverter#POSITIVE_CONVERT
     * @see ReversibleConverter#REVERSE_CONVERT
     * @see CustomStringConverter
     */
    public static StringConverter newCustomStringConverter(
        int type,
        String[] fromStrs,
        String[] toStrs
    ){
        return new CustomStringConverter(type, fromStrs, toStrs);
    }
    
    /**
     * �J�X�^��������R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @param fromChars �ϊ��㕶����z��
     * @param toChars �ϊ��Ώە�����z��
     * @return �J�X�^��������R���o�[�^
     * @see ReversibleConverter#POSITIVE_CONVERT
     * @see ReversibleConverter#REVERSE_CONVERT
     * @see CustomStringConverter
     */
    public static StringConverter newCustomStringConverter(
        int type,
        char[] fromChars,
        char[] toChars
    ){
        return new CustomStringConverter(type, fromChars, toChars);
    }
    
    /**
     * �J�X�^��������R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @param fromStrs �ϊ��㕶����z��
     * @param toStrs �ϊ��Ώە�����z��
     * @param fromChars �ϊ��㕶����z��
     * @param toChars �ϊ��Ώە�����z��
     * @return �J�X�^��������R���o�[�^
     * @see ReversibleConverter#POSITIVE_CONVERT
     * @see ReversibleConverter#REVERSE_CONVERT
     * @see CustomStringConverter
     */
    public static StringConverter newCustomStringConverter(
        int type,
        String[] fromStrs,
        String[] toStrs,
        char[] fromChars,
        char[] toChars
    ){
        return new CustomStringConverter(
            type,
            fromStrs,
            toStrs,
            fromChars,
            toChars
        );
    }
    
    /**
     * ��̃J�X�^���R���o�[�^�𐶐�����B<p>
     * 
     * @return ��̃J�X�^���R���o�[�^
     */
    public static CustomConverter newCustomConverter(){
        return new CustomConverter();
    }
    
    /**
     * �w�肵���R���o�[�^��A�������J�X�^���R���o�[�^�𐶐�����B<p>
     * 
     * @param convs �R���o�[�^�z��
     * @return �J�X�^���R���o�[�^
     */
    public static CustomConverter newCustomConverter(Converter[] convs){
        return new CustomConverter(convs);
    }
    
    /**
     * �w�肵�����K�\���̃R���o�[�^�𐶐�����B<p>
     *
     * @return ���K�\���R���o�[�^
     */
    public static PatternStringConverter patternStringConverter(){
        return new PatternStringConverter();
    }
    
    /**
     * �w�肵�����K�\���̃R���o�[�^�𐶐�����B<p>
     *
     * @param flags �}�b�`���O�t���O
     * @return ���K�\���R���o�[�^
     */
    public static PatternStringConverter patternStringConverter(int flags){
        return new PatternStringConverter(flags);
    }
    
    /**
     * �w�肵�����K�\���̃R���o�[�^�𐶐�����B<p>
     *
     * @param flags �}�b�`���O�t���O
     * @param fromStrs �ϊ����鐳�K�\��������z��
     * @param toStrs �ϊ��Ώە�����z��
     * @return ���K�\���R���o�[�^
     */
    public static PatternStringConverter patternStringConverter(
        int flags,
        String[] fromStrs,
        String[] toStrs
    ){
        return new PatternStringConverter(flags,fromStrs,toStrs);
    }
    
    private static final class StringConverterImpl
     implements StringConverter, Serializable{
        
        private static final long serialVersionUID = 1416548061709103644L;
        
        private Converter converter;
        public StringConverterImpl(Converter conv){
            converter = conv;
        }
        public StringConverterImpl(StringConverter conv){
            converter = conv;
        }
        public Object convert(Object obj) throws ConvertException{
            return converter.convert(obj);
        }
        public String convert(String obj) throws ConvertException{
            return (String)converter.convert(obj);
        }
    }
    
    private static final class CharacterConverterImpl
     implements CharacterConverter, Serializable{
        
        private static final long serialVersionUID = -3076044124853526944L;
        
        private Converter converter;
        public CharacterConverterImpl(Converter conv){
            converter = conv;
        }
        public CharacterConverterImpl(CharacterConverter conv){
            converter = conv;
        }
        public Object convert(Object obj) throws ConvertException{
            return converter.convert(obj);
        }
        public Character convert(Character c) throws ConvertException{
            return (Character)converter.convert(c);
        }
        public char convert(char c) throws ConvertException{
            if(converter instanceof CharacterConverter){
                return ((CharacterConverter)converter).convert(c);
            }else{
                return c;
            }
        }
    }
}
