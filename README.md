<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Gerenciador On Fire House</title>
  <link rel="manifest" href="manifest.json">
  <style>
    body {
      font-family: Arial, sans-serif;
      background: url('https://images.unsplash.com/photo-1612831816833-80e24f1525c5?auto=format&fit=crop&w=1350&q=80') no-repeat center center/cover;
      filter: grayscale(100%);
      margin: 0;
      padding: 0;
      color: white;
    }
    h1 {
      text-align: center;
      font-weight: bold;
      padding: 20px;
    }
    .bloco {
      background: rgba(0, 0, 0, 0.7);
      margin: 20px;
      padding: 15px;
      border-radius: 12px;
      position: relative;
      box-shadow: 0 0 10px rgba(255,255,255,0.3);
    }
    label {
      display: block;
      margin-top: 8px;
      cursor: pointer;
    }
    input[type="radio"], input[type="checkbox"] {
      margin-right: 8px;
    }
    input[type="text"], input[type="date"], input[type="time"] {
      width: 95%;
      padding: 6px;
      margin-top: 8px;
      border-radius: 8px;
      border: none;
    }
    .selecionado {
      background: #32cd32;
      border-radius: 6px;
      padding: 4px;
    }
    .botao {
      display: block;
      margin: 20px auto;
      padding: 12px 20px;
      background: #32cd32;
      border: none;
      border-radius: 12px;
      font-size: 16px;
      font-weight: bold;
      cursor: pointer;
      color: black;
      transition: 0.3s;
    }
    .botao:hover {
      background: #28a428;
    }
    .hidden {
      display: none;
    }
    .sucesso {
      text-align: center;
      font-size: 18px;
      margin-top: 20px;
      color: #32cd32;
      animation: aparecer 1s ease-in-out;
    }
    @keyframes aparecer {
      from { opacity: 0; transform: scale(0.9);} 
      to { opacity: 1; transform: scale(1);} 
    }
  </style>
</head>
<body>
  <h1>Gerenciador On Fire House</h1>
  <form id="eventoForm">
    <div class="bloco">
      <h3>Tipo de Evento</h3>
      <label><input type="radio" name="tipo" value="Encontro de Jovens"> Encontro de Jovens</label>
      <label><input type="radio" name="tipo" value="Célula"> Célula</label>
    </div>

    <div class="bloco">
      <h3>Data do Evento</h3>
      <input type="date" name="data" required>
    </div>

    <div class="bloco">
      <h3>Horário</h3>
      <input type="time" name="hora" required>
    </div>

    <div class="bloco">
      <h3>Local</h3>
      <label><input type="radio" name="local" value="Templo"> Templo</label>
      <label><input type="radio" name="local" value="Outro" id="localOutro"> Outro</label>
      <input type="text" id="localEspecifico" name="localEspecifico" maxlength="60" placeholder="Digite o local" class="hidden">
    </div>

    <div class="bloco">
      <h3>Necessidade de Banda</h3>
      <label><input type="radio" name="banda" value="Sim" id="bandaSim"> Sim</label>
      <label><input type="radio" name="banda" value="Não"> Não</label>

      <div id="instrumentos" class="hidden">
        <h4>Instrumentos</h4>
        <p>Ministrante (1 escolha, obrigatória):</p>
        <label><input type="radio" name="ministrante" value="Catherine" required> Catherine</label>
        <label><input type="radio" name="ministrante" value="Débora"> Débora</label>
        <label><input type="radio" name="ministrante" value="Rafael"> Rafael</label>
        <label><input type="radio" name="ministrante" value="Gabi"> Gabi</label>

        <p>Back's (até 4 escolhas):</p>
        <label><input type="checkbox" name="backs" value="Catherine"> Catherine</label>
        <label><input type="checkbox" name="backs" value="Daniel"> Daniel</label>
        <label><input type="checkbox" name="backs" value="Geovanna"> Geovanna</label>
        <label><input type="checkbox" name="backs" value="Gustavo"> Gustavo</label>
        <label><input type="checkbox" name="backs" value="Rafael"> Rafael</label>
        <label><input type="checkbox" name="backs" value="Gabi"> Gabi</label>

        <p>Violão:</p>
        <label><input type="radio" name="violao" value="Débora"> Débora</label>
        <label><input type="radio" name="violao" value="Daniel"> Daniel</label>

        <p>Guitarra:</p>
        <label><input type="checkbox" name="guitarra" value="Murilo"> Murilo</label>

        <p>Baixo:</p>
        <label><input type="checkbox" name="baixo" value="Davi"> Davi</label>
        <label><input type="checkbox" name="baixo" value="Isaac"> Isaac</label>

        <p>Bateria/Cajón (até 2):</p>
        <label><input type="checkbox" name="bateria" value="Lucas"> Lucas</label>
        <label><input type="checkbox" name="bateria" value="Samuel"> Samuel</label>

        <p>Teclado (obrigatório):</p>
        <label><input type="radio" name="teclado" value="Gabi" required> Gabi</label>
        <label><input type="radio" name="teclado" value="Isaac"> Isaac</label>
        <label><input type="radio" name="teclado" value="Daniel Cardim"> Daniel Cardim</label>
      </div>
    </div>

    <div class="bloco">
      <h3>Refeição</h3>
      <label><input type="radio" name="refeicao" value="Local"> Refeição Local</label>
      <label><input type="radio" name="refeicao" value="Fora" id="refeicaoFora"> Refeição Fora</label>
      <input type="text" id="refeicaoEspecifica" name="refeicaoEspecifica" maxlength="60" placeholder="Digite o local" class="hidden">
      <label><input type="radio" name="refeicao" value="Nenhuma"> Nenhuma</label>
    </div>

    <button type="submit" class="botao">Marcar Evento</button>
  </form>

  <div id="mensagemSucesso" class="sucesso hidden">✔ Evento marcado e salvo com sucesso!</div>
  <button id="novoEvento" class="botao hidden">Deseja marcar outro evento?</button>

  <script>
    const localOutro = document.getElementById('localOutro');
    const localEspecifico = document.getElementById('localEspecifico');
    const refeicaoFora = document.getElementById('refeicaoFora');
    const refeicaoEspecifica = document.getElementById('refeicaoEspecifica');
    const bandaSim = document.getElementById('bandaSim');
    const instrumentos = document.getElementById('instrumentos');
    const form = document.getElementById('eventoForm');
    const msg = document.getElementById('mensagemSucesso');
    const novoEvento = document.getElementById('novoEvento');

    localOutro.addEventListener('change', () => {
      localEspecifico.classList.remove('hidden');
    });

    refeicaoFora.addEventListener('change', () => {
      refeicaoEspecifica.classList.remove('hidden');
    });

    bandaSim.addEventListener('change', () => {
      instrumentos.classList.remove('hidden');
    });

    form.addEventListener('submit', (e) => {
      e.preventDefault();
      msg.classList.remove('hidden');
      novoEvento.classList.remove('hidden');
    });

    novoEvento.addEventListener('click', () => {
      form.reset();
      msg.classList.add('hidden');
      novoEvento.classList.add('hidden');
      instrumentos.classList.add('hidden');
      localEspecifico.classList.add('hidden');
      refeicaoEspecifica.classList.add('hidden');
    });

    if ('serviceWorker' in navigator) {
      navigator.serviceWorker.register('service-worker.js');
    }
  </script>
</body>
</html>
