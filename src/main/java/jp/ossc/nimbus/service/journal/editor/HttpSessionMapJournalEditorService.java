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

import java.io.Serializable;
import java.util.*;
import javax.servlet.http.HttpSession;

import jp.ossc.nimbus.service.journal.editorfinder.EditorFinder;

/**
 * HttpSession�I�u�W�F�N�g��Map�t�H�[�}�b�g����G�f�B�^�B<p>
 * ���̃G�f�B�^�ɂ���ĕҏW���ꂽMap�́A�ȉ��̍\�������B<br>
 * <table broder="1">
 *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="5">�l</th></tr>
 *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th colspan="4">���e</th></tr>
 *   <tr><td rowspan="3">java.lang.String</td><td rowspan="3">{@link #ATTRIBUTES_KEY}</td><td rowspan="3">java.util.Map</td><td colspan="4">�����̃}�b�v</td></tr>
 *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
 *   <tr><td>java.lang.String</td><td>������</td><td>java.lang.Object</td><td>�����l</td></tr>
 *   <tr><td>java.lang.String</td><td>{@link #ID_KEY}</td><td>java.lang.String</td><td colspan="4">�Z�b�V����ID</td></tr>
 *   <tr><td>java.lang.String</td><td>{@link #CREATION_TIME_KEY}</td><td>java.util.Date</td><td colspan="4">�Z�b�V�������쐬���ꂽ����</td></tr>
 *   <tr><td>java.lang.String</td><td>{@link #LAST_ACCESSED_TIME_KEY}</td><td>java.util.Date</td><td colspan="4">�Z�b�V�����Ɋ֘A�t����ꂽ�v�����N���C�A���g���Ō�ɑ��M��������</td></tr>
 *   <tr><td>java.lang.String</td><td>{@link #MAX_INACTIVE_INTERVAL_KEY}</td><td>java.lang.Long</td><td colspan="4">�Z�b�V������ۂ�������ő�̎��ԊԊu</td></tr>
 *   <tr><td>java.lang.String</td><td>{@link #IS_NEW_KEY}</td><td>java.lang.Boolean</td><td colspan="4">�Z�b�V�����ɎQ�����Ă��邩�ǂ���</td></tr>
 * </table>
 * �A���A�o�͂��Ȃ��悤�ɐݒ肳��Ă�����̂�A����HttpSession�Ɋ܂܂�Ă��Ȃ��������AJ2EE�̃o�[�W�����ɂ���Ď擾�ł��Ȃ����͊܂܂�Ȃ��B<br>
 * 
 * @author M.Takata
 */
public class HttpSessionMapJournalEditorService
 extends MapJournalEditorServiceBase
 implements HttpSessionMapJournalEditorServiceMBean, Serializable{
    
    private static final long serialVersionUID = 8651384368045906114L;
    
    private boolean isOutputId = true;
    private boolean isOutputCreationTime = true;
    private boolean isOutputLastAccessedTime = true;
    private boolean isOutputMaxInactiveInterval = false;
    private boolean isOutputAttributes = true;
    private boolean isOutputIsNew = true;
    
    private String[] secretAttributes;
    private Set secretAttributeSet;
    private String[] enabledAttributes;
    private Set enabledAttributeSet;
    
    public void setOutputId(boolean isOutput){
        isOutputId = isOutput;
    }
    
    public boolean isOutputId(){
        return isOutputId;
    }
    
    public void setOutputCreationTime(boolean isOutput){
        isOutputCreationTime = isOutput;
    }
    
    public boolean isOutputCreationTime(){
        return isOutputCreationTime;
    }
    
    public void setOutputLastAccessedTime(boolean isOutput){
        isOutputLastAccessedTime = isOutput;
    }
    
    public boolean isOutputLastAccessedTime(){
        return isOutputLastAccessedTime;
    }
    
    public void setOutputMaxInactiveInterval(boolean isOutput){
        isOutputMaxInactiveInterval = isOutput;
    }
    
    public boolean isOutputMaxInactiveInterval(){
        return isOutputMaxInactiveInterval;
    }
    
    public void setOutputIsNew(boolean isOutput){
        isOutputIsNew = isOutput;
    }
    
    public boolean isOutputIsNew(){
        return isOutputIsNew;
    }
    
    public void setOutputAttributes(boolean isOutput){
        isOutputAttributes = isOutput;
    }
    
    public boolean isOutputAttributes(){
        return isOutputAttributes;
    }
    
    public void setSecretAttributes(String[] names){
        secretAttributes = names;
    }
    
    public String[] getSecretAttributes(){
        return secretAttributes;
    }
    
    public void setEnabledAttributes(String[] names){
        enabledAttributes = names;
    }
    
    public String[] getEnabledAttributes(){
        return enabledAttributes;
    }
    
    public void createService(){
        secretAttributeSet = new HashSet();
        enabledAttributeSet = new HashSet();
    }
    
    public void startService(){
        if(secretAttributes != null){
            for(int i = 0; i < secretAttributes.length; i++){
                secretAttributeSet.add(secretAttributes[i]);
            }
        }
        if(enabledAttributes != null){
            for(int i = 0; i < enabledAttributes.length; i++){
                enabledAttributeSet.add(enabledAttributes[i]);
            }
        }
    }
    
    public void stopService(){
        secretAttributeSet.clear();
        enabledAttributeSet.clear();
    }
    
    public void destroyService(){
        secretAttributeSet = null;
        enabledAttributeSet = null;
    }
    
    /**
     * �W���[�i���Ƃ��ė^����ꂽHttpSession�^�̏����W���[�i���Ƃ��ďo�͂���Map���ɕϊ�����B<br>
     * 
     * @param finder �K�؂�JournalEditor��񋟂���EditorFinder
     * @param key �W���[�i���̃L�[���
     * @param value �W���[�i�����
     * @return �W���[�i���Ƃ��ďo�͂���Map���
     */
    public Map toMap(EditorFinder finder, Object key, Object value){
        final HttpSession session = (HttpSession)value;
        final Map result = new HashMap();
        
        if(isOutputId()){
            makeIdFormat(finder, key, session, result);
        }
        
        if(isOutputCreationTime()){
            makeCreationTimeFormat(finder, key, session, result);
        }
        
        if(isOutputLastAccessedTime()){
            makeLastAccessedTimeFormat(finder, key, session, result);
        }
        
        if(isOutputMaxInactiveInterval()){
            makeMaxInactiveIntervalFormat(finder, key, session, result);
        }
        
        if(isOutputIsNew()){
            makeIsNewFormat(finder, key, session, result);
        }
        
        if(isOutputAttributes()){
            makeAttributesFormat(finder, key, session, result);
        }
        return result;
    }
    
    protected Map makeIdFormat(
        EditorFinder finder,
        Object key,
        HttpSession session,
        Map map
    ){
        map.put(ID_KEY, session.getId());
        return map;
    }
    
    protected Map makeCreationTimeFormat(
        EditorFinder finder,
        Object key,
        HttpSession session,
        Map map
    ){
        map.put(CREATION_TIME_KEY, new java.util.Date(session.getCreationTime()));
        return map;
    }
    
    protected Map makeLastAccessedTimeFormat(
        EditorFinder finder,
        Object key,
        HttpSession session,
        Map map
    ){
        map.put(LAST_ACCESSED_TIME_KEY, new java.util.Date(session.getLastAccessedTime()));
        return map;
    }
    
    protected Map makeMaxInactiveIntervalFormat(
        EditorFinder finder,
        Object key,
        HttpSession session,
        Map map
    ){
        map.put(MAX_INACTIVE_INTERVAL_KEY, new Long(session.getMaxInactiveInterval()));
        return map;
    }
    
    protected Map makeIsNewFormat(
        EditorFinder finder,
        Object key,
        HttpSession session,
        Map map
    ){
        map.put(IS_NEW_KEY, session.isNew() ? Boolean.TRUE : Boolean.FALSE);
        return map;
    }
    
    protected Map makeAttributesFormat(
        EditorFinder finder,
        Object key,
        HttpSession session,
        Map map
    ){
        final Enumeration attrNames = session.getAttributeNames();
        if(!attrNames.hasMoreElements()){
            return map;
        }
        final Map subMap = new HashMap();
        while(attrNames.hasMoreElements()){
            final String name = (String)attrNames.nextElement();
            if(!enabledAttributeSet.isEmpty()
                 && !enabledAttributeSet.contains(name)){
                continue;
            }
            if(secretAttributeSet.contains(name)){
                subMap.put(name, null);
            }else{
                subMap.put(
                    name,
                    makeObjectFormat(
                        finder,
                        null,
                        session.getAttribute(name)
                    )
                );
            }
        }
        map.put(ATTRIBUTES_KEY, makeObjectFormat(finder, key, subMap));
        return map;
    }
}