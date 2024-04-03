function TC = findt(heater)
persistent T0 cnt
if (isempty(cnt))
    T0 = 23+273.15;
    cnt = 0;
end
cnt = cnt + 1;
time = [0,1];
[T] = ode15s(@(t,x)val(t,x,heater),time,T0);
TK = T(end);
T0 = TK;
noise = (rand()-0.5)*3.0;
TC = TK - 273.15 + noise;
end
