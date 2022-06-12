function jmh_results_plot()

passport.name = 'JPassport';
passport.ops = [3950836, 752707, 404050, 25093, 93783830];
passport.err = [31398, 15634, 2200, 936, 706989];

jna.name = 'JNA';
jna.ops = [939071, 410847, 258584,  20622, 1844900];
jna.err = [3132, 3000, 6857, 177, 128607];

jna_direct.name = 'JNA Direct';
jna_direct.ops = [3991461, 653279, 346008, 21723, 12900678];
jna_direct.err = [21470, 14194, 3585, 359, 331764];

jni.name = 'JNI';
jni.ops = [6171303,696180, 358446, 21872, 91683788];
jni.err = [44771, 4546, 1538, 428, 1455563];


data = [jna, jna_direct, jni, passport];
do_plot('Array length 32', data, 1);
do_plot('Array length 512', data, 2);
do_plot('Array length 1024', data, 3);
do_plot('Array length 16384', data, 4);
do_plot('Passing Primatives', data, 5);

end

function do_plot(plot_name, data, idx)
    
    figure(idx);
    x = 1:length(data);
    values = zeros(length(x), 1);
    err_bars = zeros(length(x), 1);
    names = {};
    
    for i=1:length(data)
        values(i) = data(i).ops(idx);
        err_bars(i) = data(i).err(idx);
        names{i} = data(i).name;
    end
    
    bar(x, values);
    xticklabels(names);
    hold on
    
    er = errorbar(x, values, err_bars);
    er.Color = [0, 0, 0];
    er.LineStyle = 'none';    
    title(plot_name);
    ylabel('Ops/s');
    hold off;
    saveas(idx, [plot_name '.png']);
end