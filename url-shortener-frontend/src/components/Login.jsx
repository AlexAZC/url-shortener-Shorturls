import { useForm } from "react-hook-form"
import CampoTexto from "./CampoTexto";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../api/api";
import toast from "react-hot-toast";
import { useStoreContext } from "../contextApi/ContextApi";



const Login = () => {


  const navegacion = useNavigate();
  const [cargar, setCargar] = useState(false);
  const {setToken} = useStoreContext();


  const {
    register,
    handleSubmit,
    reset,
    formState: {errors}
  } = useForm({
    defaultValues: {
      username:"",
      password:"",
    },
    mode:"onTouched",
  });

  const loginHandler = async (data) => {
      setCargar(true);
      try {
         const {data:response} = await api.post(
          "/api/auth/public/login",
          data
        );
        toast.success("Inicio de Sesión exitoso, redireccionando...");
        console.log(data);
        console.log(response.token);
        setToken(response.token);
        
        //Guardar el token en el local storage
        localStorage.setItem("JWT_TOKEN", JSON.stringify(response.token));

        reset();
        navegacion("/dashboard");


        
      } catch (error) {
        toast.error("Acceso fallido!")
      } finally {
        setCargar(false);
      }
  };

  return (
    <div className="min-h-[calc(100vh-64px)] flex justify-center items-center ">
        <form onSubmit={handleSubmit(loginHandler)}
               className="sm:w-[450px] w-[360px]  shadow-custom py-8 sm:px-8 px-4 rounded-md">
                <h1 className="text-center font-serif text-btnColor font-bold lg:text-3xl text-2xl">
                   Inicia Sesion en Shorturl
                </h1>

                <hr className="mt-2 mb-5 text-black" />

                <div className="flex flex-col gap-3">
                    <CampoTexto 
                      label="Nombre"
                      required
                      id="username"
                      type="text"
                      message="*Se requiere nombre de usuario"
                      placeholder="Ingrese su nombre de usuario"
                      register={register}
                      errors={errors}
                    /> 

                    
                    <CampoTexto 
                      label="Contraseña"
                      required
                      id="password"
                      type="password"
                      message="*Se requiere contraseña"
                      placeholder="Ingrese su contraseña"
                      register={register}
                      min={6}
                      errors={errors}
                    />
                </div>
                
                <button
                  disabled={cargar}
                  type="submit"
                  className="bg-customRed font-semibold text-white  bg-custom-gradient w-full py-2 hover:text-slate-400 transition-colors duration-100 rounded-sm my-3">
                  {cargar ? "Iniciando sesión..." : "Iniciar sesión"}
                </button>

                <p className="text-center text-sm text-slate-700 mt-6">
                  ¿No tienes una cuenta?
                  <Link 
                    className="font-semibold underline hover:text-black ml-0.5"
                    to="/registro">
                      <span className="text-btnColor">Regístrate</span>
                  </Link>
                </p>

        </form>
    </div>
  )
}
export default Login