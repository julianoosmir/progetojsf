package com.mycompany.paginacpf.service;

import com.mycompany.paginacpf.model.ContextDto;
import com.mycompany.paginacpf.model.DataForm;
import com.mycompany.paginacpf.model.ResponseDTO;

import java.util.List;

public class Verificador {
    SearchContextsService service = new SearchContextsService();

    public Boolean verificarServiceContext(DataForm userDaform){
       ResponseDTO responseDTO = service.runRequest(userDaform);
       if(responseDTO.getStatus() == 200 && verificarListaDto(responseDTO.getBody())){
           List<ContextDto> contextDtos = (List<ContextDto>) responseDTO.getBody();
           return veficarContexto(contextDtos);
       }
       return false;
    }

    private Boolean veficarContexto(List<ContextDto> contextDtos) {

        boolean contem = false;
        for (ContextDto dto: contextDtos) {
            if (dto.getProfileContext().contains("_I")) {
                contem = true;
                break;
            };
        }
        return contem;
    }

    private boolean verificarListaDto(Object body) {
        if (body instanceof List<?>){
            List<?> list = (List<?>) body;
            return list.get(0) instanceof ContextDto;
        }
        return false;
    }
}
