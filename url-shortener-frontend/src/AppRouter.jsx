import { Toaster } from "react-hot-toast"
import BarNav from "./components/BarNav"
import PaginaUrlCorta from "./components/PaginaUrlCorta"
import {Route,Routes } from "react-router-dom"
import "./App.css"
import PagInicio from "./components/PagInicio"
import AcercaDe from "./components/AcercaDe"
import PagRegistro from "./components/PagRegistro"
import Login from "./components/Login"
import DisenoPanel from "./components/panel/DisenoPanel"
import Footer from "./components/Footer"
import PrivateRoute from "./PrivateRoute"
import ErrorPage from "./components/ErrorPage"


const AppRouter = () => {
 
  return (
    <>
        <BarNav />
        <Toaster position='bottom-center' />
        <Routes>
          <Route path='/' element={<PagInicio />}/>
          <Route path='/about' element={<AcercaDe />}/>
          {/*<Route path='/s/:url' element={<PaginaUrlCorta />}/>*/}
          
          <Route path='/registro' element={<PrivateRoute publicPage={true}><PagRegistro /></PrivateRoute> }/>
          <Route path='/login' element={<PrivateRoute publicPage={true}><Login /></PrivateRoute> } />

          <Route path='/dashboard' element={<PrivateRoute publicPage={false}><DisenoPanel /></PrivateRoute> } />
          <Route path='error' element={<ErrorPage />} />
          <Route path='*' element={<ErrorPage message="No podemos encontrar la pagina que usted esta buscando" />} />


        </Routes>
        <Footer />
    </>
  )
}
export default AppRouter

export const SubDomainRouter = () => {
    return (
        <Routes>
          <Route path='/:url' element={<PaginaUrlCorta />}/>
        </Routes>
    )
}