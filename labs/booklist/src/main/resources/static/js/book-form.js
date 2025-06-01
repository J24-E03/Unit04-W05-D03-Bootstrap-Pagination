const inputElement = document.querySelectorAll("input")

inputElement.forEach((oneInput)=>{
    oneInput.addEventListener("invalid",(e)=>{
        e.preventDefault()

        document.querySelector(`#${oneInput.id}Error`)
            .style.display="block"
    })
})