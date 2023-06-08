import data from "./products.json";
export default defineEventHandler(async () => {

    return new Promise<any> ((resolve)=>{
     
        setTimeout(()=>{
            console.log('Hola');
            resolve(data);
        }, 2000)
    });
})

