package ru.joolsoul.model.stepModel;

public enum StepType {
    START, // делаются принудительные ставки (блайнды и анте)
    PRE_FLOP, // делаются ставки со стартовыми картами
    FLOP, // делаются ставки с картами на столе
    TURN,
    RIVER,
    SHOWDOWN; // показываются карты
}
