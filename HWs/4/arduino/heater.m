function TC = heater(heater)
persistent cnt a
if (isempty(cnt))
   try
      a = arduino;
      disp(a)
   catch
      com_port = input('port: ','s');
      a = arduino(com_port,'Uno');
      disp(a)
   end
   cnt = 0;
end      
%v rd
v1 = @() readVoltage(a, 'A0');
TC = @(V) (V - 0.5)*100.0;    
%tempr rd
T1C = @() TC(v1());
% LED function (0 <= level <= 1)
led = @(level) writePWMDutyCycle(a,'D9',max(0,min(1,level)));  % ON
%output (0 <= heater <= 100)
% limit to 0-0.9 (0-100%)
h1 = @(level) writePWMDutyCycle(a,'D3',max(0,min(100,level))*0.9/100);
cnt = cnt + 1;
%rd
TC = T1C();
%wr
h1(heater);
%high tempr with LED
if TC > 40
    led(1)
else
    led(0)    
end

end