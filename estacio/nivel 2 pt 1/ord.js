const Add = () => {
  event.preventDefault();
  let valor = document.getElementById("valores").value;
  let novoItem = document.createElement("li");
  novoItem.textContent = valor;
  let lista = document.getElementById("lista");
  lista.appendChild(novoItem);
  document.getElementById("valores").value = "";
  document.getElementById("valores").focus();
};

const Misturar = () => {
  event.preventDefault();
  let list = document.getElementById("lista");
  let itens = Array.from(list.children);
  itens.sort(() => Math.random() - 0.5);
  list.innerHTML = "";
  itens.forEach((item) => {
    list.appendChild(item);
  });
};

const BlubbleSort = (itens) => {
  event.preventDefault();
  var len = itens.length;
  var swapped;
  do {
    swapped = false;

    for (var i = 0; i < len - 1; i++) {
      if (itens[i] > itens[i + 1]) {
        var temp = itens[i];
        itens[i] = itens[i + 1];
        itens[i + 1] = temp;
        swapped = true;
      }
    }
    len--;
  } while (swapped);
  return itens;
};

const SelectionSort = (itens) => {
  event.preventDefault();
};

const QuickSort = (itens) => {
  event.preventDefault();
};

const Ordenar = () => {
  event.preventDefault();
  let list = document.getElementById("lista");
  let itens = Array.from(list.children);
  let select = document.getElementById("meuSelect");
  let seletor = select.selectedIndex;
  list.innerHTML='';
  if (seletor === 0) {
    BlubbleSort(itens).forEach((item) => {
        list.appendChild(item);
    });
  }
  if (seletor === 1) {
    SelectionSort(itens);
  }
  if (seletor === 2) {
    QuickSort(itens);
  }
};
