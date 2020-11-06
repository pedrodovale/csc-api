package com.pvale.project.csc.api.exception;

public class CscExpiredCertificateException extends CscApiException {

    private static final long serialVersionUID = 3271903444915244332L;

    private String subjectDn;

    public CscExpiredCertificateException(String subjectDn) {
        this.subjectDn = subjectDn;
    }

    public CscExpiredCertificateException(String message, String subjectDn) {
        super(message);
        this.subjectDn = subjectDn;
    }

    public CscExpiredCertificateException(String message, Throwable cause, String subjectDn) {
        super(message, cause);
        this.subjectDn = subjectDn;
    }

    public CscExpiredCertificateException(Throwable cause, String subjectDn) {
        super(cause);
        this.subjectDn = subjectDn;
    }

    public CscExpiredCertificateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String subjectDn) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.subjectDn = subjectDn;
    }

    public String getSubjectDn() {
        return subjectDn;
    }
}