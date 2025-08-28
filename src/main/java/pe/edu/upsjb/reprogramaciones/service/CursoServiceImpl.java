

package pe.edu.upsjb.reprogramaciones.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upsjb.reprogramaciones.dto.*;
import pe.edu.upsjb.reprogramaciones.dao.*;


@Service
@Transactional


public class CursoServiceImpl implements CursoService {


    @Autowired
    private CursoDao cursoDao;


    public ListaCursoResponse consultarCurso(CursoRequest request) {
        return cursoDao.consultarCurso(request);
    }


}
