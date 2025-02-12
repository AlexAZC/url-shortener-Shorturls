import { subDomainList } from "./constant";

export const getApps = () => {
    const subDomain = getSubDomain(window.location.hostname);
        console.log("location: ",window.location.hostname);
        console.log("subDomain: ", subDomain);
    const mainApp = subDomainList.find((app) => app.main);
        console.log("mainApp: ", mainApp);
        
    if(subDomain === "") return mainApp.app;

    const apps = subDomainList.find((app) => subDomain === app.subdomain)
        console.log("apps: ",apps);
        

    return apps ? apps.app : mainApp.app;
}



export const getSubDomain = (location) => {
    
   const locationParts = location.split(".");
        console.log("locationParts: ",locationParts);
    const isLocalhost = locationParts.slice(-1)[0] === "localhost";
        console.log("isLocalhost: ",isLocalhost);
    const sliceTill = isLocalhost ? -1 : -2;
        console.log("sliceTill: ",sliceTill);
        console.log("ultimo: ",locationParts.slice(0,sliceTill).join(""));
        
    return locationParts.slice(0,sliceTill).join("");
}