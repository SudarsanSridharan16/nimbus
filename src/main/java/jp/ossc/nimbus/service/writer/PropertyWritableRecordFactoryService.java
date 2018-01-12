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

import java.util.*;
import java.lang.reflect.*;

import jp.ossc.nimbus.beans.NoSuchPropertyException;
import jp.ossc.nimbus.beans.Property;
import jp.ossc.nimbus.beans.SimpleProperty;
import jp.ossc.nimbus.beans.PropertyFactory;
import jp.ossc.nimbus.service.log.*;

/**
 * �v���p�e�BWritableRecord�t�@�N�g���T�[�r�X�B<p>
 * {@link #setFormat(String)}�Őݒ肳�ꂽ�t�H�[�}�b�g�̃L�[�ɊY������l��{@link #createRecord(Object)}�̈����Ŏw�肳�ꂽ�C�ӂ̃I�u�W�F�N�g������A�v���p�e�B�A�N�Z�X���Ď擾����B<br>
 * �v���p�e�B�A�N�Z�X�Ƃ́ABean�̃v���p�e�B�ɑ΂���A�N�Z�X�̎��ŁA�p�ӂ���Ă���A�N�Z�X���@�́A{@link PropertyFactory}���Q�ƁB<br>
 * 
 * @author M.Takata
 */
public class PropertyWritableRecordFactoryService
 extends WritableRecordFactoryService
 implements PropertyWritableRecordFactoryServiceMBean, java.io.Serializable{
    
    private static final long serialVersionUID = 6929876971079349458L;
    
    // ���b�Z�[�WID��`
    private static final String PWRF_ = "PWRF_";
    private static final String PWRF_0 = PWRF_ + 0;
    private static final String PWRF_00 = PWRF_0 + 0;
    private static final String PWRF_000 = PWRF_00 + 0;
    private static final String PWRF_0000 = PWRF_000 + 0;
    private static final String PWRF_00001 = PWRF_0000 + 1;
    
    private static final String ITERATE_SUFFIX = "*";
    
    private Properties formatKeyMapping;
    private Map writableRecordPropertyMapping;
    
    private Map iterateFormatKeyMappings;
    private Map iterateWritableRecordPropertyMappings;
    
    private Map iterateFormats;
    private Map iterateFormatMappings;
    
    // PropertyWritableRecordFactoryServiceMBean��JavaDoc
    public void setFormatKeyMapping(Properties mapping){
        formatKeyMapping = mapping;
    }
    // PropertyWritableRecordFactoryServiceMBean��JavaDoc
    public Properties getFormatKeyMapping(){
        return formatKeyMapping;
    }
    
    // PropertyWritableRecordFactoryServiceMBean��JavaDoc
    public void setIterateFormatKeyMapping(String key, Properties mapping){
        iterateFormatKeyMappings.put(key, mapping);
    }
    // PropertyWritableRecordFactoryServiceMBean��JavaDoc
    public Properties getIterateFormatKeyMapping(String key){
        return (Properties)iterateFormatKeyMappings.get(key);
    }
    
    // PropertyWritableRecordFactoryServiceMBean��JavaDoc
    public void setIterateFormat(String key, String format){
        iterateFormats.put(key, format);
    }
    // PropertyWritableRecordFactoryServiceMBean��JavaDoc
    public String getIterateFormat(String key){
        return (String)iterateFormats.get(key);
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        super.createService();
        writableRecordPropertyMapping = new HashMap();
        iterateFormatKeyMappings = new HashMap();
        iterateWritableRecordPropertyMappings = new HashMap();
        iterateFormats = new HashMap();
        iterateFormatMappings = new HashMap();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        super.startService();
        if(formatKeyMapping != null){
            final Iterator keys = formatKeyMapping.keySet().iterator();
            while(keys.hasNext()){
                final String key = (String)keys.next();
                final String prop = formatKeyMapping.getProperty(key);
                if(prop != null && prop.length() != 0){
                    Property property = PropertyFactory.createProperty(prop);
                    property.setIgnoreNullProperty(true);
                    writableRecordPropertyMapping.put(key, property);
                }
            }
        }
        if(iterateFormats.size() != 0){
            final Iterator itrKeys = iterateFormats.keySet().iterator();
            while(itrKeys.hasNext()){
                final String itrKey = (String)itrKeys.next();
                final String format = (String)iterateFormats.get(itrKey);
                iterateFormatMappings.put(itrKey, parseFormat(format));
                if(!iterateFormatKeyMappings.containsKey(itrKey)){
                    throw new IllegalArgumentException(
                        "IterateFormatKeyMapping that corresponds to \"" + itrKey + "\" is not specified."
                    );
                }
            }
        }
        if(iterateFormatKeyMappings.size() != 0){
            final Iterator itrKeys = iterateFormatKeyMappings.keySet().iterator();
            while(itrKeys.hasNext()){
                final String itrKey = (String)itrKeys.next();
                final Properties props = (Properties)iterateFormatKeyMappings.get(itrKey);
                final Map mapping = new HashMap();
                final Iterator keys = props.keySet().iterator();
                while(keys.hasNext()){
                    final String key = (String)keys.next();
                    final String prop = props.getProperty(key);
                    if(prop != null && prop.length() != 0){
                        Property property = PropertyFactory.createProperty(prop);
                        property.setIgnoreNullProperty(true);
                        mapping.put(key, property);
                    }
                }
                iterateWritableRecordPropertyMappings.put(itrKey, mapping);
            }
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        writableRecordPropertyMapping.clear();
        iterateWritableRecordPropertyMappings.clear();
        iterateFormatMappings.clear();
        super.stopService();
    }
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        writableRecordPropertyMapping = null;
        iterateFormatKeyMappings = null;
        iterateWritableRecordPropertyMappings = null;
        iterateFormats = null;
        iterateFormatMappings = null;
        super.destroyService();
    }
    
    /**
     * �w�肳�ꂽ�o�͗v�f�����v���p�e�B���̏W�����擾����B<p>
     *
     * @param elements �o�͗v�f
     * @return �v���p�e�B���̏W��
     */
    protected Set getElementKeys(Object elements){
        if(elements instanceof Map){
            return super.getElementKeys(elements);
        }
        return SimpleProperty.getPropertyNames(elements);
    }
    
    protected WritableElement createElement(String key, Object val){
        return createElement(
            key,
            val,
            writableRecordPropertyMapping
        );
    }
    
    protected WritableElement createElement(
        String key,
        Object val,
        Map propMapping
    ){
        if(key.endsWith(ITERATE_SUFFIX)){
            Object[] array = null;
            if(val == null){
                return super.createElement(key, val);
            }else if(val instanceof Collection){
                Collection col = (Collection)val;
                array = col.toArray(new Object[col.size()]);
            }else if(val.getClass().isArray()){
                array = (Object[])val;
            }else{
                return super.createElement(key, val);
            }
            if(array == null || array.length == 0){
                return null;
            }
            IterateWritableElement itrElement = new IterateWritableElement(key);
            if(iterateFormatMappings.containsKey(key)){
                final List parsedElements
                     = (List)iterateFormatMappings.get(key);
                final Map itrPropMapping
                     = (Map)iterateWritableRecordPropertyMappings.get(key);
                for(int i = 0; i < array.length; i++){
                    for(int j = 0, jmax = parsedElements.size(); j < jmax; j++){
                        final ParsedElement parsedElem
                             = (ParsedElement)parsedElements.get(j);
                        WritableElement element = null;
                        if(parsedElem.isKeyElement()){
                            final String itrElementKey = parsedElem.getValue();
                            element = createElement(
                                itrElementKey,
                                getElementValue(
                                    itrElementKey,
                                    array[i],
                                    itrPropMapping
                                )
                            );
                        }else{
                            element = new SimpleElement(parsedElem.getValue());
                        }
                        if(element != null){
                            itrElement.addElement(element);
                        }
                    }
                }
            }else{
                for(int i = 0; i < array.length; i++){
                    final WritableElement element
                         = super.createElement(key, array[i]);
                    if(element != null){
                        itrElement.addElement(element);
                    }
                }
            }
            return itrElement;
        }else{
            return super.createElement(key, val);
        }
    }
    
    /**
     * �w�肳�ꂽ�o�͗v�f����A�w�肳�ꂽ�v���p�e�B���̒l���擾����B<p>
     *
     * @param key �v���p�e�B��
     * @param elements �o�͗v�f
     * @return �o�͗v�f���̃v���p�e�B�l
     */
    protected Object getElementValue(String key, Object elements){
        return getElementValue(
            key,
            elements,
            writableRecordPropertyMapping
        );
    }
    
    protected Object getElementValue(
        String key,
        Object elements,
        Map propMapping
    ){
        if(propMapping != null && propMapping.containsKey(key)){
            final Logger logger = getLogger();
            final Property prop = (Property)propMapping.get(key);
            try{
                return prop.getProperty(elements);
            }catch(NoSuchPropertyException e){
                return null;
            }catch(InvocationTargetException e){
                logger.write(PWRF_00001, key, e);
                return null;
            }
        }else{
            return super.getElementValue(key, elements);
        }
    }
}
