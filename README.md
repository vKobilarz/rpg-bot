# Input do Usuário
O usuário enviará eventos a partir de um chat pelo Slack ou pelo Discord.

Cada Evento poderá acionar somente uma ação.

O evento seguirá o seguinte padrão:

[Ação] [Parâmetro 1] [Parâmetro 2] ...

# Atributos
### Vitalidade
* Aumenta a vida máxima do personagem.

### Fortitude
* Aumenta as defesas bases do personagem.
* Aumenta a energia máxima do personagem.

### Agilidade
* Aumenta a chance de aparar inimigos ao defender.
* Aumenta a chance de desviar ataques ao receber ataques.
* Aumenta a prioridade de turno.

### Foco
* Aumenta a mana máxima do personagem.
* Aumenta o limite de habilidades.

### Força
* Atributo utilizado no cálculo de dano.

### Destreza
* Atributo utilizado no cálculo de dano.

### Inteligência
* Atributo utilizado no cálculo de dano.

### Fé
* Atributo utilizado no cálculo de dano.

# Tipos de Armas
### Armas normais
* Possuem dano e agilidade equilibrada.
* Seu dano aumenta de acordo com a força e destreza do personagem.
* Armas equilibradas costumam ter um aumento na chance e dano crítico.

### Armas pesadas
* Possuem dano aumentado, mas reduz a agilidade do personagem.
* Seu dano aumenta de acordo com a força do personagem.

### Armas leves
* Possuem dano reduzido, mas aumentam a agilidade do personagem.
* Seu dano aumenta de acordo com a destreza do personagem.
* Armas leves costumam ter um grande aumento na chance e dano crítico.

### Armas mágicas
* Possuem dano reduzido, mas aumentam o dano de habilidades relacionadas a inteligência.

### Armas místicas
* Possuem dano reduzido, mas aumentam o efeito de habilidades relacionadas a fé.

# Combate
* O combate é baseado em turnos.
* A sequência dos turnos é determinada pela agilidade dos personagens.
* O combate é feito entre dois grupos.
* Cada grupo pode ter de 1 até 4 participantes.

# Ações disponíveis
### Start
Esse é o comando que o jogador deve executar para iniciar o jogo pela primeira vez. Esse comando irá fazer o cadastro inicial do jogador.

### Travel [Location]
Movimenta o jogador para a área fornecida em [Location].

### Inventory
Visualiza os itens equipados, não equipados e consumíveis.

### Equip [EquippableItem]
Equipa o item fornecido em [EquippableItem]

### Use [ConsumableItem] [Quantity]
Consome um item fornecido em [ConsumableItem].
* Caso [Quantity] esteja presente, consome essa quantidade ao invés de um item.
* Caso usado em combate, consumirá o turno atual.

### Scout
Explora a área atual do jogador. Isso pode fazer com que o jogador encontre itens, entre em combate, etc.
* Os itens possíveis de serem encontrados depende da [Location] em que o jogador está presente.

## Ações relacionadas a combate
### Attack
Realiza o ataque básico.
* O dano do ataque básico dependerá do dano da arma que está sendo utilizada.
* Recupera uma quantidade da mana do personagem.
* Consome uma quantidade de energia do personagem.
* Consome o turno atual.

### Defend
Reduz o próximo dano recebido.
* A quantidade reduzida de dano dependerá dos atributos defensivos do personagem, podendo anular completamente o dano.
* Consome uma quantidade de energia do jogador. A quantidade dependerá do valor mitigado pela defesa.
* É possível que a defesa apare o ataque inimigo. A chance de aparar dependerá dos atributos de agilidade do personagem.
* Consome o turno atual.

### Cast [Skill]
Utiliza a habilidade fornecida em [Skill].
* O efeito da [Skill] dependerá do dano da arma que está sendo utilizada.
* A [Skill] consumirá uma quantidade da mana do personagem.
* Consome o turno atual.

# Efeitos
### Aparar
* Ao aparar um inimigo, coloque o inimigo em estado vulnerável.

### Vulnerável
* Enquanto o personagem estiver nesse estado, o próximo ataque recebido possui 100% de chance crítica. O efeito acaba ao receber dano.
