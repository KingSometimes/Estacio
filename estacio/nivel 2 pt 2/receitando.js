var receitas = [
  {
    titulo: "Arroz de couve flor",
    imagem: "/mundo 1/download.jpg",
    preparo:
      "Deixe a Couve-flor picada. Adicione os ingredientes e refogue bem. Adicione sal, tampe a panela e deixe cozinhar.",
    ingredientes: ["arroz", "couve flor", "cebola", "azeite"],
  },
  {
    titulo: "Bolo de cafe",
    imagem: "/mundo 1/download (1).jpg",
    preparo:
      "Bata o Acucar, as gemas e o cafe. Adcione farinha e chocolate e mexa bem. Bata as claras e Junte a mistura.",
    ingredientes: [
      "farinha de trigo",
      "chocolate em po",
      "cafe coado",
      "acucar",
      "ovos",
    ],
  },
  {
    titulo: "Coxinha de brigadeiro",
    imagem: "/mundo 1/download (2).jpg",
    preparo:
      "junte o leite condesado, chocolate em po e manteiga. Aqueca no fogo baixo. Envolva os morangos e passe no granulado.",
    ingredientes: [
      "leite condensado",
      "chocolate em po",
      "manteiga",
      "chocolate granulado",
      "morango",
    ],
  },
];

function getListaIngredientes(receita) {
  var lista = receita.ingredientes
    .map((ingrediente) => `<li>${ingrediente}</li>`)
    .reduce((html, item) => html + item, "");

  return `<ul>${lista}</ul>`;
}

function getCard(receita) {
  return `
      <div class="card" style="width: 250px">
        <img src="${receita.imagem}" class="card-img-top" alt="${
    receita.titulo
  }">
        <div class="card-body">
          <h5 class="card-title">${receita.titulo}</h5>
          <div class="card-text">
            ${getListaIngredientes(receita)}
            <hr>
            ${receita.preparo}
          </div>
        </div>
      </div>
    `;
}

function preencheCatalogo() {
  var pnlCatalogo = document.getElementById("pnlCatalogo");
  var cardsHTML = receitas
    .map((receita) => getCard(receita))
    .reduce((html, card) => html + card, "");
  pnlCatalogo.innerHTML = cardsHTML;
}
