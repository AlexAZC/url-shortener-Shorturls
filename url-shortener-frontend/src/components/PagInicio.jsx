import { useNavigate } from "react-router-dom"
import Card from "./Card"
import { motion } from "framer-motion"
import { useStoreContext } from "../contextApi/ContextApi"


const PagInicio = () => {


    let desc = "Crea enlaces cortos y memorables con facilidad gracias a Shorturls. Nuestra interfaz intuitiva te permite generar enlaces únicos en segundos. Comparte tus URLs sin esfuerzo en todas las plataformas y optimiza tu estrategia de difusión con Shorturls. Rastrea clics, gestiona tus enlaces de manera sencilla y mejora tu presencia en línea como nunca antes."

    const navigate = useNavigate();
    const {token} = useStoreContext();
    
    console.log("TOKEN FROM LANDING PAGE: " + token);
    

    const dashBoardHandler = () => {

    }

  return (
    <div className="min-h-[calc(100vh-64px)]  lg:px-14 sm:px-8 px-4">
        <div className="lg:flex-row flex-col    lg:py-5   pt-16   lg:gap-10 gap-8 flex justify-between items-center">
            <div className=" flex-1"> 
                <motion.h1 
                initial={{opacity:0, y:-80}}
                whileInView={{opacity:1,y:0,}}
                viewport={{once:true}}
                transition={{duration:0.8}}
                className="font-bold font-roboto text-slate-800 md:text-5xl text-3xl md:leading-[55px] sm:leading-[45px] leading-10 lg:w-full md:w-[70%] w-full">
                    Shorturls simplifica el acortamiento de enlaces para compartir de manera eficiente
                </motion.h1>
                <p className="text-slate-700 text-1xl my-5">
                Shorturls hace que acortar enlaces sea muy sencillo compartiendo tus enlaces de forma eficiente y sin esfuerzo. Gracias a su interfaz amigable, Shorturls te permite generar URLs cortas y fáciles de compartir en segundos. ¡Simplifica tu experiencia de compartir con Shorturl hoy mismo!
                </p>
                <div className="flex items-center gap-3">
                    <motion.button  
                     initial={{ opacity: 0, y: 80 }}
                     whileInView={{
                       opacity: 1,
                       y: 0,
                     }}
                     viewport={{ once: true }}
                     transition={{ duration: 0.8 }}
                     onClick={dashBoardHandler}
                    className="bg-custom-gradient  w-40 text-white rounded-md  py-2">
                        Administra tus links
                    </motion.button>
                    <motion.button  
                    initial={{ opacity: 0, y: 80 }}
                    whileInView={{
                      opacity: 1,
                      y: 0,
                    }}
                    viewport={{ once: true }}
                    transition={{ duration: 0.8 }}
                    onClick={dashBoardHandler}
                    className="border-btnColor border w-40 text-btnColor rounded-md  py-2 ">
                        Acorta tu Link/Url
                    </motion.button>
                </div>
            </div>

            <div className="flex-1 flex justify-center w-full">
                <motion.img 
                 initial={{ opacity: 0 }}
                 whileInView={{
                   opacity: 1,
                 }}
                 viewport={{ once: true }}
                 transition={{ duration: 0.8 }}
                className="sm:w-[480px] w-[400px] object-cover rounded-md " 
                src="images/logoshorturl.png" alt="logo" />
            </div>
        </div>
        <div className="sm:pt-12 pt-7" >
            <motion.p 
             initial={{ opacity: 0, y: 50 }}
             whileInView={{
               opacity: 1,
               y: 0,
             }}
             viewport={{ once: true }}
             transition={{ duration: 0.8 }}
            className="text-slate-800 font-roboto font-bold lg:w-[60%]  md:w-[70%] sm:w-[80%] mx-auto text-3xl text-center">
                La elección de personas y equipos en las empresas más destacadas del mundo
            </motion.p>

            <div className="pt-4 pb-7 grid lg:gap-7 gap-4 xl:grid-cols-4  lg:grid-cols-3 sm:grid-cols-2 grid-cols-1 mt-4">
                <Card 
                titulo="Acorta tus Urls de manera sencilla"
                desc="Descubre lo sencillo que es generar URLs únicas con solo unos clics. Nuestra interfaz intuitiva y un proceso de configuración rápido te garantizan empezar a acortar enlaces sin complicaciones."
                />
                <Card
                titulo="Conoce el análisis avanzado"
                desc="Explora a fondo cómo funcionan tus enlaces con nuestro completo panel de análisis. Rastrea clics, datos geográficos y fuentes de referencia para perfeccionar tus estrategias de marketing y maximizar tu impacto."
                />
                <Card
                titulo="Seguridad administrada para tus enlaces"
                desc="Comparte tus URLs con total confianza. Todos los enlaces acortados están protegidos por encriptación avanzada, asegurando que tus datos permanezcan seguros en todo momento."
                />
                <Card
                titulo="Redirecciones ultrarrápidas y disponibilidad garantizada"
                desc="Disfruta de redirecciones instantáneas y un tiempo de actividad excepcional gracias a nuestra infraestructura confiable. Tus enlaces estarán siempre disponibles y listos para ofrecer una experiencia impecable a tus usuarios."
                />
            </div>
        </div>

    </div>
  )
}
export default PagInicio