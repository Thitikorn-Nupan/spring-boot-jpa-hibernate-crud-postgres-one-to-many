package com.ttknpdev.crudpostgresonetomany.log;

import com.ttknpdev.crudpostgresonetomany.aspect.StudentAspectLogging;
import com.ttknpdev.crudpostgresonetomany.aspect.SubjectAspectLogging;
import com.ttknpdev.crudpostgresonetomany.daos.DaoStudent;
import com.ttknpdev.crudpostgresonetomany.daos.DaoSubject;
import com.ttknpdev.crudpostgresonetomany.restclient.TestRestClient;
import org.apache.log4j.Logger;

public class Logging {
    protected static final Logger daoStudent = Logger.getLogger(DaoStudent.class);
    protected static final Logger daoSubject = Logger.getLogger(DaoSubject.class);
    protected static final Logger aspectStudent = Logger.getLogger(StudentAspectLogging.class);
    protected static final Logger aspectSubject = Logger.getLogger(SubjectAspectLogging.class);
    protected static final Logger testRestClient = Logger.getLogger(TestRestClient.class);
}
