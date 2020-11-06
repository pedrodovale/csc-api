package com.pvale.project.csc.api.exception;

public class CscRevokedCertificateException extends CscApiException {

    private static final long serialVersionUID = -6759763579063401999L;

    private String subjectDn;

    public CscRevokedCertificateException(String subjectDn) {
        this.subjectDn = subjectDn;
    }

    public CscRevokedCertificateException(String message, String subjectDn) {
        super(message);
        this.subjectDn = subjectDn;
    }

    public CscRevokedCertificateException(String message, Throwable cause, String subjectDn) {
        super(message, cause);
        this.subjectDn = subjectDn;
    }

    public CscRevokedCertificateException(Throwable cause, String subjectDn) {
        super(cause);
        this.subjectDn = subjectDn;
    }

    public CscRevokedCertificateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String subjectDn) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.subjectDn = subjectDn;
    }

    public String getSubjectDn() {
        return subjectDn;
    }
}