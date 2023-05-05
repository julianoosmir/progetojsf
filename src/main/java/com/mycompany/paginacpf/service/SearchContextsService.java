package com.mycompany.paginacpf.service;

import com.mycompany.paginacpf.model.DataForm;
import com.mycompany.paginacpf.model.ResponseDTO;

public class SearchContextsService {
    private MethodMock contexts = new MethodMock();
    public ResponseDTO runRequest(DataForm userDataForm) {
        return contexts.runRequest(userDataForm);
    }
}
