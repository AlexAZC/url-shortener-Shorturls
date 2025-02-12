
import { FaLink, FaShareAlt, FaEdit, FaChartLine } from "react-icons/fa";

const AcercaDe = () => {
  return (
    <div className="lg:px-14 sm:px-8 px-5 min-h-[calc(100vh-64px)] pt-2">
      <div className="bg-white w-full sm:py-10 py-8  ">
        <h1 className="sm:text-4xl text-slate-800 text-3xl font-bold italic  mb-3">
          Acerca de Shorturls
        </h1>
        <p className="text-gray-700 text-sm  mb-8 xl:w-[60%] lg:w-[70%] sm:w-[80%] w-full ">
        Shorturls: la forma más sencilla y poderosa de acortar tus enlaces
Transforma tus URLs largas en enlaces cortos y profesionales en segundos. Con Shorturls, no solo simplificas la forma en que compartes, ¡también tienes el control total! Genera, gestiona y analiza tus enlaces acortados de manera eficiente.
Haz que cada clic cuente y lleva tu experiencia de compartir al siguiente nivel con Shorturls.
        </p>
        <div className="space-y-5 xl:w-[60%] lg:w-[70%] sm:w-[80%] w-full ">
          <div className="flex items-start">
            <FaLink className="text-blue-500 text-3xl mr-4" />
            <div>
              <h2 className="sm:text-2xl font-bold text-slate-800">
                Acorta tu Url de forma sencilla
              </h2>
              <p className="text-gray-600">
              Crea enlaces cortos y memorables en cuestión de segundos
              Descubre lo fácil que es transformar tus URLs con solo unos clics. Nuestra interfaz intuitiva y proceso de configuración rápida te permiten empezar a acortar enlaces sin complicaciones. ¡Simplifica, comparte y sorprende con cada enlace!
              </p>
            </div>
          </div>
          <div className="flex items-start">
            <FaShareAlt className="text-green-500 text-3xl mr-4" />
            <div>
              <h2 className="sm:text-2xl font-bold text-slate-800">
                Analiza tus rutas
              </h2>
              <p className="text-gray-600">
              Conoce el impacto de tus enlaces con análisis avanzados
              Descubre todo sobre el rendimiento de tus enlaces con nuestro completo panel de análisis. Rastrea clics, datos geográficos y fuentes de referencia para perfeccionar tus estrategias de marketing. ¡Convierte cada enlace en una herramienta poderosa para alcanzar tus objetivos!
              </p>
            </div>
          </div>
          <div className="flex items-start">
            <FaEdit className="text-purple-500 text-3xl mr-4" />
            <div>
              <h2 className="sm:text-2xl font-bold text-slate-800">
                Obtención de seguridad
              </h2>
              <p className="text-gray-600">
              Seguridad de nivel superior para tus enlaces
              Confía en nuestras sólidas medidas de protección. Cada enlace acortado está respaldado por una avanzada encriptación, asegurando que tus datos estén siempre seguros. ¡Comparte con tranquilidad, estamos a tu lado!
              </p>
            </div>
          </div>
          <div className="flex items-start">
            <FaChartLine className="text-red-500 text-3xl mr-4" />
            <div>
              <h2 className="sm:text-2xl font-bold text-slate-800">
                Rápido y confiable
              </h2>
              <p className="text-gray-600">
              Redirecciones ultrarrápidas y disponibilidad garantizada
              Disfruta de redirecciones instantáneas y un tiempo de actividad excepcional gracias a nuestra infraestructura confiable. Tus enlaces acortados estarán siempre disponibles y listos para ofrecer una experiencia fluida a tus usuarios.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
export default AcercaDe