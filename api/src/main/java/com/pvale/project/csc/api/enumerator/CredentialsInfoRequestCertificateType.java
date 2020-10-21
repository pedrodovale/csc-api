/**
 * Copyright 2002-2020 MULTICERT S.A.
 * All rights reserved.
 *
 * This source is provided for inspection purposes and recompilation only,
 * unless specified differently in a contract with MULTICERT S.A.. This
 * source has to be kept in strict confidence and must not be disclosed to any
 * third party under any circumstances. Redistribution in source and binary
 * forms, with or without modification, are NOT permitted in any case!
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * Should any term or condition of this representation be deemed invalid or
 * inefficient, it will not affect the validity and efficiency of the remainder
 * of the Contract.
 */
package com.pvale.project.csc.api.enumerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum CredentialsInfoRequestCertificateType {

	@JsonProperty("none")
	NONE,
	@JsonProperty("single")
	SINGLE,
	@JsonProperty("chain")
	CHAIN;

	@JsonValue
	public String getSerializedValue() {
		return this.name().toLowerCase(Locale.getDefault());
	}
}