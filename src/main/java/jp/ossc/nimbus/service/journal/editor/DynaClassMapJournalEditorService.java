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
package jp.ossc.nimbus.service.journal.editor;

import java.util.*;
import java.io.Serializable;

import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;

import jp.ossc.nimbus.service.journal.editorfinder.EditorFinder;

/**
 * {@link DynaClass}��Map�t�H�[�}�b�g����G�f�B�^�B<p>
 * DynaClass�̃v���p�e�B���}�b�v�Ɋi�[����B<br>
 * 
 * @author M.Takata
 */
public class DynaClassMapJournalEditorService
 extends MapJournalEditorServiceBase
 implements DynaClassMapJournalEditorServiceMBean, Serializable{
    
    private static final long serialVersionUID = -2766951676020202091L;
    
    private boolean isOutputName = true;
    private boolean isOutputDynaProperties = true;
    
    public void setOutputName(boolean isOutput){
        isOutputName = isOutput;
    }
    
    public boolean isOutputName(){
        return isOutputName;
    }
    
    public void setOutputDynaProperties(boolean isOutput){
        isOutputDynaProperties = isOutput;
    }
    
    public boolean isOutputDynaProperties(){
        return isOutputDynaProperties;
    }
    
    /**
     * �W���[�i���Ƃ��ė^����ꂽDynaClass�^�̏����W���[�i���Ƃ��ďo�͂���Map���ɕϊ�����B<br>
     * 
     * @param finder �K�؂�JournalEditor��񋟂���EditorFinder
     * @param key �W���[�i���̃L�[���
     * @param value �W���[�i�����
     * @return �W���[�i���Ƃ��ďo�͂���Map���
     */
    public Map toMap(EditorFinder finder, Object key, Object value){
        final DynaClass dynaClass = (DynaClass)value;
        final Map result = new HashMap();
        if(isOutputName()){
            makeNameFormat(finder, key, dynaClass, result);
        }
        
        if(isOutputDynaProperties()){
            makeDynaPropertiesFormat(finder, key, dynaClass, result);
        }
        
        return result;
    }
    
    protected Map makeNameFormat(
        EditorFinder finder,
        Object key,
        DynaClass dynaClass,
        Map map
    ){
        map.put(NAME_KEY, dynaClass.getName());
        return map;
    }
    
    protected Map makeDynaPropertiesFormat(
        EditorFinder finder,
        Object key,
        DynaClass dynaClass,
        Map map
    ){
        final DynaProperty[] props = dynaClass.getDynaProperties();
        if(props == null || props.length == 0){
            return map;
        }
        final Map subMap = new HashMap();
        for(int i = 0, max = props.length; i < max; i++){
            final String name = props[i].getName();
            subMap.put(name, props[i]);
        }
        map.put(DYNA_PROPERTIES_KEY, makeObjectFormat(finder, key, subMap));
        return map;
    }
}