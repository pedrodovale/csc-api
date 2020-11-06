package com.pvale.project.csc.api.exception;

public class CscSuspendedCertificateException extends CscApiException {

    private static final long serialVersionUID = 886406530914322608L;

    private String subjectDn;

    public CscSuspendedCertificateException(String subjectDn) {
        this.subjectDn = subjectDn;
    }

    public CscSuspendedCertificateException(String message, String subjectDn) {
        super(message);
        this.subjectDn = subjectDn;
    }

    public CscSuspendedCertificateException(String message, Throwable cause, String subjectDn) {
        super(message, cause);
        this.subjectDn = subjectDn;
    }

    public CscSuspendedCertificateException(Throwable cause, String subjectDn) {
        super(cause);
        this.subjectDn = subjectDn;
    }

    public CscSuspendedCertificateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String subjectDn) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.subjectDn = subjectDn;
    }

    public String getSubjectDn() {
        return subjectDn;
    }
}