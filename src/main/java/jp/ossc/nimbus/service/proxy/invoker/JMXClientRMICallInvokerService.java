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
package jp.ossc.nimbus.service.proxy.invoker;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;
import java.lang.reflect.*;
import javax.management.*;
import javax.management.remote.*;
import javax.naming.NamingException;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.jndi.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.proxy.RemoteServiceCallException;


/**
 * JMX�N���C�A���gRMI�Ăяo��Invoker�B<p>
 * JNDI����lookup����javax.management.MBeanServerConnection���g����MBean���Ăяo���B
 *
 * @author M.Takata
 */
public class JMXClientRMICallInvokerService extends ServiceBase
 implements Invoker, java.io.Serializable,
            JMXClientRMICallInvokerServiceMBean{
    
    private static final long serialVersionUID = -4668783322226114794L;
    
    private static final String SETTER_PREFIX = "set";
    private static final int SETTER_PREFIX_LENGTH = 3;
    private static final String GETTER_PREFIX = "get";
    private static final int GETTER_PREFIX_LENGTH = 3;
    
    protected ServiceName jndiFinderServiceName;
    protected JndiFinder jndiFinder;
    protected String rmiAdaptorName = DEFAULT_JMX_RMI_ADAPTOR_NAME;
    protected String serviceURL;
    protected Map jmxConnectorEnvironment;
    protected JMXConnector jmxConnector;
    protected String objectNameDomain;
    protected Properties objectNameProperties;
    protected String objectNameStr;
    protected ObjectName objectName;
    protected String mBeanQuery;
    protected String objectNameRegex;
    
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public void setJndiFinderServiceName(ServiceName name){
        jndiFinderServiceName = name;
    }
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public ServiceName getJndiFinderServiceName(){
        return jndiFinderServiceName;
    }
    
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public void setRMIAdaptorName(String name){
        rmiAdaptorName = name;
    }
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public String getRMIAdaptorName(){
        return rmiAdaptorName;
    }
    
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public void setServiceURL(String url){
        serviceURL = url;
    }
    
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public String getServiceURL(){
        return serviceURL;
    }
    
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public void setJMXConnectorEnvironment(Map env){
        jmxConnectorEnvironment = env;
    }
    
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public Map getJMXConnectorEnvironment(){
        return jmxConnectorEnvironment;
    }
    
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public void setObjectName(String name){
        objectNameStr = name;
    }
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public String getObjectName(){
        return objectNameStr;
    }
    
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public void setObjectNameDomain(String domain){
        objectNameDomain = domain;
    }
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public String getObjectNameDomain(){
        return objectNameDomain;
    }
    
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public void setObjectNameProperties(Properties prop){
        objectNameProperties = prop;
    }
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public Properties getObjectNameProperties(){
        return objectNameProperties;
    }

    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public void setMBeanQuery(String query){
        mBeanQuery = query;
    }
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public String getMBeanQuery(){
        return mBeanQuery;
    }

    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public void setObjectNameRegex(String regex){
        objectNameRegex = regex;
    }
    // JMXClientRMICallInvokerServiceMBean��JavaDoc
    public String getObjectNameRegex(){
        return objectNameRegex;
    }

    /**
     * javax.management.MBeanServerConnection��JNDI����lookup����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X��ݒ肷��B<p>
     *
     * @param jndiFinder JndiFinder�T�[�r�X
     */
    public void setJndiFinder(JndiFinder jndiFinder) {
        this.jndiFinder = jndiFinder;
    }

    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(jndiFinderServiceName != null){
            jndiFinder = (JndiFinder)ServiceManagerFactory.getServiceObject(jndiFinderServiceName);
        }
        if(serviceURL == null && jndiFinder == null) {
            throw new IllegalArgumentException(
                "JndiFinderServiceName or JndiFinder must be specified."
            );
        }
        if(serviceURL != null){
            jmxConnector = JMXConnectorFactory.newJMXConnector(
                new JMXServiceURL(serviceURL),
                jmxConnectorEnvironment
            );
        }
        
        if(mBeanQuery != null){
            if(objectNameRegex == null){
                throw new IllegalArgumentException(
                    "objectNameRegex must be specified."
                );
            }
        }else if(objectNameStr == null){
            if(objectNameDomain == null){
                throw new IllegalArgumentException(
                    "objectNameDomain must be specified."
                );
            }
            if(objectNameProperties == null){
                throw new IllegalArgumentException(
                    "objectNameProperties must be specified."
                );
            }
        }
    }

    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        if(jmxConnector != null){
            jmxConnector.close();
            jmxConnector = null;
        }
    }

    /**
     * �ݒ肳��Ă��鐳�K�\�����g���āAObjectName����������B<p>
     * 
     * @return ObjectName
     * @exception IOException 
     * @exception NamingException 
     * @exception MalformedObjectNameException 
     */
    protected ObjectName matchObjectName()
        throws MalformedObjectNameException, NamingException, IOException{
        ObjectName[] names = queryMBeans();
        if(names == null || names.length == 0){
            return null;
        }

        for(int i = 0; i < names.length; i++){
            Pattern pattern = Pattern.compile(objectNameRegex);
            if(pattern.matcher(names[i].toString()).matches()){
                return names[i];
            }
        }
        
        return null;
    }

    /**
     * �ݒ肳��Ă���query���g���āAObjectName�̔z���₢���킹��B<p>
     * 
     * @return ObjectName�̔z��
     * @exception NamingException 
     * @exception IOException 
     * @exception MalformedObjectNameException 
     */
    protected ObjectName[] queryMBeans()
        throws NamingException, MalformedObjectNameException, IOException{
        MBeanServerConnection connection = 
            (MBeanServerConnection)jndiFinder.lookup(rmiAdaptorName);
        
        Set mbeans = connection.queryNames(new ObjectName(mBeanQuery), null);
        if(mbeans == null || mbeans.size() == 0){
            return null;
        }
        
        return (ObjectName[])mbeans.toArray(new ObjectName[mbeans.size()]);
    }
    
    /**
     * ObjectName���쐬����B<p>
     * 
     * @return ObjectName
     * @exception Exception
     */
    protected ObjectName createObjectName() throws Exception{
        if(objectName == null){
            if(mBeanQuery != null){
                objectName = matchObjectName();
            }else if(objectNameStr != null){
                objectName = new ObjectName(objectNameStr);
            }else{
                objectName = new ObjectName(objectNameDomain, new Hashtable(objectNameProperties));
            }
        }

        return objectName;
    }

    /**
     * JMX�o�R��MBean���Ăяo���B<p>
     * JNDI����javax.management.MBeanServerConnection��lookup���āAMBean�̌Ăяo�����s���B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂����ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invoke(InvocationContext context) throws Throwable{
        final MethodInvocationContext methodContext
             = (MethodInvocationContext)context;
        try{
            MBeanServerConnection connection = null;
            if(jndiFinder != null){
                connection = (MBeanServerConnection)jndiFinder.lookup(rmiAdaptorName);
            }else{
                jmxConnector.connect();
                connection = jmxConnector.getMBeanServerConnection();
            }
            final Method method = methodContext.getTargetMethod();
            final String methodName = method.getName();
            final Object[] params = methodContext.getParameters();
            if(methodName.length() > SETTER_PREFIX_LENGTH
                 && methodName.startsWith(SETTER_PREFIX)
                 && params != null && params.length == 1){
                
                final Attribute attr = new Attribute(
                    methodName.substring(SETTER_PREFIX_LENGTH),
                    params[0]
                );
                connection.setAttribute(createObjectName(), attr);
                return null;
            }else if(methodName.length() > GETTER_PREFIX_LENGTH
                 && methodName.startsWith(GETTER_PREFIX)
                 && (params == null || params.length == 0)){
                return connection.getAttribute(
                    createObjectName(),
                    methodName.substring(GETTER_PREFIX_LENGTH)
                );
            }else{
                String[] sigs = null;
                Class[] paramTypes = methodContext.getTargetMethod().getParameterTypes();
                if(paramTypes != null){
                    sigs = new String[paramTypes.length];
                    for(int i = 0; i < paramTypes.length; i++){
                        sigs[i] = paramTypes[i].getName();
                    }
                }
                return connection.invoke(
                    createObjectName(),
                    methodName,
                    params,
                    sigs
                );
            }
        }catch(javax.naming.NamingException e){
            throw new RemoteServiceCallException(e);
        }catch(java.rmi.RemoteException e){
            throw new RemoteServiceCallException(e);
        }catch(InstanceNotFoundException e){
            throw new RemoteServiceCallException(e);
        }catch(AttributeNotFoundException e){
            throw new RemoteServiceCallException(e);
        }catch(InvalidAttributeValueException e){
            throw new RemoteServiceCallException(e);
        }catch(MBeanException e){
            throw new RemoteServiceCallException(e);
        }catch(ReflectionException e){
            throw new RemoteServiceCallException(e);
        }catch(IOException e){
            throw new RemoteServiceCallException(e);
        }
    }
}
