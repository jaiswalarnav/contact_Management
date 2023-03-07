package com.contact.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse implements RestResponse {

	private int status;

	private String message;

	private Object data;

}
