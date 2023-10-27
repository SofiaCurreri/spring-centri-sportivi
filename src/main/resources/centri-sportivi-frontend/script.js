const CENTRI_SPORTIVI_API_URL = "http://localhost:8080/api/v1/centri-sportivi";
const contentDOM = document.getElementById("content");

//funzione per ottenere centri sportivi
const getCentriSportivi = async() => {
    try{
        const response = await axios.get(CENTRI_SPORTIVI_API_URL);
        console.log(response);
        return response.data;
    } catch (error) {
        console.log(error);
    }
};

//funzione per creare gruppo card con dati centri sportivi
const listaCentriSportivi = (data) => {
    if(data.length > 0){
        let lista = `<div class="row g-4">`;

        data.forEach((element) => {
            lista += `
            <div class="col-4">
                <div class="card">
                    <h5 class="card-header text-center"><i class="fa-solid fa-building fs-1"></i></i></h5>
                    <div class="card-body d-flex flex-column  align-items-center ">
                        <h5 class="card-title">${element.nome}</h5>
                        <p class="card-text">${element.indirizzo}</p>
                        <p class="card-text">${element.citta}</p>
                        <a href="#" class="btn btn-primary">Vai al dettaglio</a>
                    </div>
                </div>
            </div>`;
        });

        lista += `</div>`;

        return lista;
    } else {
        return `<div class="mt-4 alert alert-info"> Al momento non sono presenti centri sportivi </div>`;
    }
};

//funzione che aspetta di ottenere i dati dei centri sportiv per poi metterli in listaCentriSportivi()
const loadData = async () => {
    const data = await getCentriSportivi();
    contentDOM.innerHTML = listaCentriSportivi(data);
};

loadData();