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
package jp.ossc.nimbus.service.test.resource;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.Utility;
import jp.ossc.nimbus.io.CSVReader;
import jp.ossc.nimbus.service.test.TemplateEngine;

/**
 * Apache Velocity���g����{@link TemplateEngine}�T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public class VelocityTemplateEngineService extends ServiceBase implements TemplateEngine, VelocityTemplateEngineServiceMBean{
    
    private static final long serialVersionUID = 710504283828129889L;
    
    private File templateResourceDirectory;
    private Properties properties;
    
    private VelocityEngine engine;
    private CSVReader csvReader;
    
    public void setTemplateResourceDirectory(File dir){
        templateResourceDirectory = dir;
    }
    public File getTemplateResourceDirectory(){
        return templateResourceDirectory;
    }
    
    public void setProperties(Properties props){
        properties = props;
    }
    public Properties getProperties(){
        return properties;
    }
    
    public void setCSVReader(CSVReader reader){
        csvReader = reader;
    }
    
    public void startService() throws Exception{
        engine = new VelocityEngine();
        Properties props = properties == null ? new Properties() : properties;
        if(templateResourceDirectory != null){
            props.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templateResourceDirectory.getCanonicalPath());
        }
        props.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, Boolean.FALSE.toString());
        engine.init(props);
    }
    
    public void stopService() throws Exception{
        engine = null;
    }
    
    /**
     * �e���v���[�g�t�@�C���ƃf�[�^�t�@�C����ǂݍ���ŁA�ϊ����s���o�̓t�@�C���ɏ����o���B<p>
     * �e���v���[�g�t�@�C���́AApache Velocity��VTL(Velocity Template Language)�ŋL�q����B<br>
     * �f�[�^�t�@�C���́AVTL�ŎQ�Ƃ���I�u�W�F�N�g���A2��ނ̋L�q���@�����݂����āA�����L�q�ł���B<br>
     * �P�́A�v���p�e�B�`���ŁA"�ϐ���=�l"�Ŏw�肷��B�����w�肷��ꍇ�́A���s���ċL�q����B<br>
     * �����P�́ACSV�`���ŁA1�s�ڂɕϐ����A2�s�ڂɃv���p�e�B���A�R�s�ڈȍ~�ɒl���L�q����B�v���p�e�B���́ACSV�`���łP�s�̂݋L�q����B�l�́ACSV�`���ŕ����s�L�q�ł���B�l�̍s�̏I�[�������ɂ́A��s��}������B���̕ϐ����Q�Ƃ���ƁA�w�肵���l�̍s������List�ŁA���̗v�f�ɂ́A�v���p�e�B���ƒl���i�[���ꂽMap���i�[����Ă���B<br>
     * 
     *
     * @param tmplateFile �e���v���[�g�t�@�C��
     * @param dataFile �f�[�^�t�@�C��
     * @param outputFile �o�̓t�@�C��
     * @param encoding �����G���R�[�f�B���O�B�e���v���[�g�t�@�C���A�f�[�^�t�@�C���́A���������G���R�[�f�B���O�ł���K�v������A�o�̓t�@�C�����A���̕����G���R�[�f�B���O�ƂȂ�B
     * @exception Exception �ϊ��Ɏ��s�����ꍇ
     */
    public void transform(File tmplateFile, File dataFile, File outputFile, String encoding) throws Exception{
        if(dataFile == null){
            final FileInputStream fis  = new FileInputStream(templateResourceDirectory == null ? tmplateFile : new File(templateResourceDirectory, tmplateFile.getPath()));
            final FileOutputStream fos = new FileOutputStream(outputFile);
            try{
                byte[] buf = new byte[1024];
                int len = 0;
                while((len = fis.read(buf)) != -1){
                    fos.write(buf, 0, len);
                }
            }finally{
                fis.close();
                fos.close();
            }
        }else{
            Template template = encoding == null ? engine.getTemplate(tmplateFile.getPath()) : engine.getTemplate(tmplateFile.getPath(), encoding);
            Context context = new VelocityContext();
            CSVReader reader = csvReader == null ? new CSVReader() : csvReader.cloneReader();
            reader.setReader(encoding == null ? new FileReader(dataFile) : new InputStreamReader(new FileInputStream(dataFile), encoding));
            try{
                String line = null;
                while((line = reader.readLine()) != null){
                    if(line.length() == 0){
                        continue;
                    }
                    final int index = line.indexOf("=");
                    if(index == -1){
                        String name = line;
                        String[] propNames = reader.readCSVLine();
                        List propValues = null;
                        List records = new ArrayList();
                        while((propValues = reader.readCSVLineList(propValues)) != null && propValues.size() != 0){
                            Map record = new HashMap();
                            for(int i = 0; i < propNames.length; i++){
                                String value = replaceProperty((String)propValues.get(i));
                                record.put(propNames[i], value);
                            }
                            records.add(record);
                        }
                        context.put(name, records);
                    }else{
                        String value = replaceProperty(line.substring(index + 1));
                        context.put(line.substring(0, index), value);
                    }
                }
            }finally{
                reader.close();
                reader = null;
            }
            
            Writer writer = encoding == null ? new FileWriter(outputFile) : new OutputStreamWriter(new FileOutputStream(outputFile), encoding);
            try{
                template.merge(context, writer);
            }finally{
                writer.close();
                writer = null;
            }
        }
    }
    
    private String replaceProperty(String textValue) {
        textValue = Utility.replaceSystemProperty(textValue);
        if (getServiceLoader() != null) {
            textValue = Utility.replaceServiceLoderConfig(textValue, getServiceLoader().getConfig());
        }
        if (getServiceManager() != null) {
            textValue = Utility.replaceManagerProperty(getServiceManager(), textValue);
        }
        textValue = Utility.replaceServerProperty(textValue);
        
        return textValue;
    }
}