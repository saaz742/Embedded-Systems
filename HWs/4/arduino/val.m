function dTdt = val(T,heater)

m = 4 / 1000; % kg (4 gm)
U = 10; % W/m^2-K
A = 12 / 100^2; % m^2
c = 0.01; % W/(heater input, 0-100)
Cp = 500.0; % J/kg-K
Ta = 23 + 273.15; % K
sigma = 5.67e-8; % W/m^2-K^4
eps = 0.9; % emisivity

dTdt = (U*A * (Ta - T) + ...
    sigma * eps * A * (Ta^4 - T^4) + ...
    c * heater) / (m*Cp); 
end