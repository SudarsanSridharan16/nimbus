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

/**
 * {@link ServletRequestJSONJournalEditorService}のMBeanインタフェース<p>
 * 
 * @author M.Takata
 * @see ServletRequestJSONJournalEditorService
 */
public interface ServletRequestJSONJournalEditorServiceMBean
 extends JSONJournalEditorServiceMBean{
    
    public static final String PROPERTY_SENT_SERVER = "SentServer";
    public static final String PROPERTY_RECEIVED_SERVER = "ReceivedServer";
    public static final String PROPERTY_HOST = "Host";
    public static final String PROPERTY_PROTOCOL = "Protocol";
    public static final String PROPERTY_SCHEME = "Scheme";
    public static final String PROPERTY_LOCALE = "Locale";
    public static final String PROPERTY_CONTENT_TYPE = "ContentType";
    public static final String PROPERTY_CONTENT_LENGTH = "ContentLength";
    public static final String PROPERTY_CHARACTER_ENCODING = "CharacterEncoding";
    public static final String PROPERTY_ATTRIBUTES = "Attributes";
    public static final String PROPERTY_PARAMETERS = "Parameters";
    
    public void setSecretAttributes(String[] names);
    public String[] getSecretAttributes();
    
    public void setEnabledAttributes(String[] names);
    public String[] getEnabledAttributes();
    
    public void setDisabledAttributes(String[] names);
    public String[] getDisabledAttributes();
    
    public void setSecretParameters(String[] names);
    public String[] getSecretParameters();
    
    public void setEnabledParameters(String[] names);
    public String[] getEnabledParameters();
    
    public void setDisabledParameters(String[] names);
    public String[] getDisabledParameters();
}
