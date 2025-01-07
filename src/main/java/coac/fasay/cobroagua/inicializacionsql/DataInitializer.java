package coac.fasay.cobroagua.inicializacionsql;

import coac.fasay.cobroagua.Repository.*;
import coac.fasay.cobroagua.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private AgenciaRepository agenciaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        if(empresaRepository.count() == 0) {
            Empresa empresa = new Empresa();
            empresa.setCodigo(1);
            empresa.setNombre("Cooperativa de Ahorro y Crédito Fasayñan Ltda.");
            empresa.setTelefono("2294119");
            empresa.setDireccion("Manuel Reyes y Luis Rio Rodriguez");
            empresaRepository.save(empresa);
        }

        if (rolRepository.count() == 0) {
            Rol rol = new Rol();
            rol.setCodigo(1);
            rol.setNombre("Administrador");
            rolRepository.save(rol);
            Rol rol2 = new Rol();
            rol2.setCodigo(2);
            rol2.setNombre("Cajero");
            rolRepository.save(rol2);
        }

        if (cargoRepository.count() == 0) {
            Cargo cargo = new Cargo();
            cargo.setCodigo(1);
            cargo.setNombre("Cajero");
            cargo.setDescripcion("Departamento de Cajas");
            cargoRepository.save(cargo);
        }

        if(agenciaRepository.count() == 0) {
            Agencia agencia = new Agencia();
            Empresa empresa = new Empresa();
            agencia.setCodigo(1);
            agencia.setDireccion("Gualaceo");
            agencia.setTelefono("2294119");
            agencia.setTipo_servicio("Financiero");
            empresa.setCodigo(1);
            agencia.setEmpresa(empresa);
            agenciaRepository.save(agencia);

            Agencia agencia2 = new Agencia();
            Empresa empresa2 = new Empresa();
            agencia2.setCodigo(2);
            agencia2.setDireccion("Delegsol");
            agencia2.setTelefono("2294119");
            agencia2.setTipo_servicio("Financiero");
            empresa2.setCodigo(1);
            agencia2.setEmpresa(empresa2);
            agenciaRepository.save(agencia2);

            Agencia agencia3 = new Agencia();
            Empresa empresa3 = new Empresa();
            agencia3.setCodigo(3);
            agencia3.setDireccion("Chordeleg");
            agencia3.setTelefono("2294119");
            agencia3.setTipo_servicio("Financiero");
            empresa3.setCodigo(1);
            agencia3.setEmpresa(empresa3);
            agenciaRepository.save(agencia3);

            Agencia agencia4 = new Agencia();
            Empresa empresa4 = new Empresa();
            agencia4.setCodigo(4);
            agencia4.setDireccion("Cuenca");
            agencia4.setTelefono("2294119");
            agencia4.setTipo_servicio("Financiero");
            empresa4.setCodigo(1);
            agencia4.setEmpresa(empresa4);
            agenciaRepository.save(agencia3);

            Agencia agencia5 = new Agencia();
            Empresa empresa5 = new Empresa();
            agencia5.setCodigo(5);
            agencia5.setDireccion("Principal");
            agencia5.setTelefono("2294119");
            agencia5.setTipo_servicio("Financiero");
            empresa5.setCodigo(1);
            agencia5.setEmpresa(empresa5);
            agenciaRepository.save(agencia5);

        }

            if (usuarioRepository.count() == 0) {
            Usuario usuario = new Usuario();
            Rol roluser = new Rol();
            Cargo cargouser = new Cargo();
            Agencia agenciauser = new Agencia();

            // Obtener la fecha actual como LocalDate
            LocalDate currentDate = LocalDate.now();
            // Convertir LocalDate a java.sql.Date
            Date sqlDate = Date.valueOf(currentDate);

            usuario.setCodigo(1);
            usuario.setNombre("Admin");
            usuario.setApellido("Admin");
            usuario.setUser("admin");
            usuario.setPassword("fasay.123");
            usuario.setEstado('A');
            usuario.setFechaCreacion(sqlDate);

            roluser.setCodigo(1);
            cargouser.setCodigo(1);
            agenciauser.setCodigo(1);

            usuario.setRol(roluser);
            usuario.setCargo(cargouser);
            usuario.setAgencia(agenciauser);
            usuarioRepository.save(usuario);

            System.out.println("Fecha en java.sql.Date: " + sqlDate);

        }
    }
}
