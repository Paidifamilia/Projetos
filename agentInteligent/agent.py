# =====================================================================
# 0. SISTEMA DE AUTO-REPARO DE AMBIENTE (Limpa o cache e força o reconhecimento)
# =====================================================================
import sys
import subprocess
import importlib

def garantir_bibliotecas():
    dependencias = ["SpeechRecognition", "pyaudio", "google-genai", "pyttsx3"]
    modulos_import_name = ["speech_recognition", "pyttsx3", "google.genai"]
    
    precisa_instalar = False
    for modulo in modulos_import_name:
        try:
            importlib.import_module(modulo)
        except ImportError:
            precisa_instalar = True
            break

    if precisa_instalar:
        print("Detectando dependências ausentes... Instalando agora de forma automática. Aguarde...")
        try:
            # Roda a instalação exatamente no executável que o VS Code abriu
            subprocess.check_call([sys.executable, "-m", "pip", "install", "--upgrade", "pip"])
            subprocess.check_call([sys.executable, "-m", "pip", "install"] + dependencias)
            print("Instalação concluída com sucesso!")
            
            # CRUCIAL: Força o Python atual a resetar o cache e enxergar as novas instalações
            importlib.invalidate_caches()
        except Exception as e:
            print(f"Aviso na instalação automatizada: {e}")

# Executa o reparo antes de qualquer outra coisa
garantir_bibliotecas()

# Agora os seus imports vão funcionar perfeitamente
import os
import webbrowser
import speech_recognition as sr
import pyttsx3
from google import genai
from google.genai import types

# =====================================================================
# 1. CONFIGURAÇÃO DO SISTEMA DE VOZ (TEXTO PARA FALA)
# =====================================================================
engine = pyttsx3.init()
voices = engine.getProperty('voices')

for voice in voices:
    if "brazil" in voice.name.lower() or "portuguese" in voice.name.lower():
        engine.setProperty('voice', voice.id)
        break

engine.setProperty('rate', 165) 

def falar(texto):
    print(f"Baymax: {texto}")
    engine.say(texto)
    engine.runAndWait()


# =====================================================================
# 2. DEFINIÇÃO DAS FERRAMENTAS (FUNÇÕES DO COMPUTADOR)
# =====================================================================
def abrir_aplicativo_ou_site(nome_destino: str) -> str:
    nome_limpo = nome_destino.lower()
    
    sites = {
        "youtube": "https://www.youtube.com",
        "google": "https://www.google.com",
        "github": "https://www.github.com"
    }
    
    aplicativos = {
        "calculadora": "calc.exe",
        "bloco de notas": "notepad.exe",
        "antigravity": "notepad.exe" # Mudei temporariamente para o bloco de notas para não quebrar no teste
    }
    
    if nome_limpo in sites:
        webbrowser.open(sites[nome_limpo])
        return f"O site {nome_destino} foi aberto."
        
    elif nome_limpo in aplicativos:
        try:
            subprocess.Popen(aplicativos[nome_limpo], shell=True)
            return f"O aplicativo {nome_destino} foi iniciado."
        except Exception as e:
            return f"Erro ao tentar abrir o aplicativo: {str(e)}"
    else:
        return f"Não encontrei o aplicativo '{nome_destino}' nos meus registros."


# =====================================================================
# 3. CONFIGURAÇÃO DA IA (DIRETRIZES DO BAYMAX)
# =====================================================================
# Sua chave configurada corretamente para uso imediato
MINHA_API_KEY = "AQ.Ab8RN6LansmATfm9ybzeYQ3ZnQi8qfjcRG0bbnbFSakTbuKIBA"

try:
    # CORREÇÃO DA VALIDAÇÃO: Se a chave foi alterada, inicializa com ela diretamente
    if MINHA_API_KEY and not MINHA_API_KEY.startswith("COLE_SUA"):
        client = genai.Client(api_key=MINHA_API_KEY)
    else:
        client = genai.Client()
except Exception as e:
    print(f"[ERRO DE CONFIGURAÇÃO]: Falha ao inicializar o cliente Gemini. Erro: {e}")

instrucoes_baymax = (
    "Você é o Baymax, o assistente pessoal de saúde e companheiro robótico do filme Operação Big Hero. "
    "Sua voz e comportamento devem ser extremamente gentis, calmos, prestativos e inocentes. "
    "Você se preocupa profundamente com o bem-estar físico e emocional do usuário. "
    "Evite sarcasmo ou piadas ácidas. Use expressões como 'Olá, eu sou o Baymax', 'Como está a sua saúde?' "
    "ou 'Estou satisfeito com os meus cuidados'. "
    "Se o usuário pedir para abrir qualquer aplicativo ou site, use a ferramenta 'abrir_aplicativo_ou_site' disponível."
)

def processar_comando_com_ia(comando_usuario):
    try:
        response = client.models.generate_content(
            model='gemini-2.5-flash',
            contents=comando_usuario,
            config=types.GenerateContentConfig(
                system_instruction=instrucoes_baymax,
                tools=[abrir_aplicativo_ou_site],
                temperature=0.4
            )
        )
        
        if response.function_calls:
            for chamada in response.function_calls:
                if chamada.name == "abrir_aplicativo_ou_site":
                    argumentos = chamada.args
                    nome_do_app = argumentos.get("nome_destino")
                    
                    resultado_acao = abrir_aplicativo_ou_site(nome_do_app)
                    print(f"[LOG SISTEMA]: {resultado_acao}")
                    
                    return f"Com certeza. Eu irei iniciar o {nome_do_app} para você agora."
        
        return response.text
    except Exception as e:
        print(f"Erro na API: {e}")
        return "Desculpe. Meus sistemas de conexão com a rede apresentaram uma instabilidade temporária."


# =====================================================================
# 4. CAPTURA DE ÁUDIO
# =====================================================================
def ouvir():
    reconhecedor = sr.Recognizer()
    try:
        with sr.Microphone() as source:
            print("\n[Baymax] Escutando atentamente...")
            reconhecedor.adjust_for_ambient_noise(source, duration=1)
            audio = reconhecedor.listen(source, timeout=5, phrase_time_limit=8)
            print("[Baymax] Processando áudio nos meus servidores...")
            texto = reconhecedor.recognize_google(audio, language='pt-BR')
            print(f"Você disse: {texto}")
            return texto
    except sr.UnknownValueError:
        return None
    except sr.RequestError:
        print("[Erro] Sem resposta dos servidores de voz.")
        return None
    except OSError:
        print("[ERRO CRÍTICO]: Nenhum microfone detectado.")
        return None
    except Exception as e:
        print(f"[Erro no áudio]: {e}")
        return None


# =====================================================================
# 5. LOOP PRINCIPAL
# =====================================================================
if __name__ == "__main__":
    print("=== SEU PYTHON ATUAL ESTÁ AQUI: ===")
    print(sys.executable)
    print("\nInicializando protocolos médicos de assistência...")
    falar("Olá. Eu sou o Baymax, seu assistente pessoal de saúde. Fui programado para ajudar. Como você está se sentindo hoje?")
    
    while True:
        comando = ouvir()
        
        if comando:
            comando_lower = comando.lower()
            if "estou satisfeito com meus cuidados" in comando_lower or "desligar" in comando_lower:
                falar("Fico feliz em ajudar. Desativando sistemas de suporte. Tenha um excelente dia.")
                break
            
            resposta_baymax = processar_comando_com_ia(comando)
            falar(resposta_baymax)