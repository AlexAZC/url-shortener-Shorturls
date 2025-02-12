import Itemcorto from "./Itemcorto"

const ListaUrlsCortos = ({data}) => {
  return (
    <div className="my-6 space-y-4">
        {data.map((item) => {
            return <Itemcorto key={item.id} {...item}/>
        })}
    </div>
  )
}
export default ListaUrlsCortos