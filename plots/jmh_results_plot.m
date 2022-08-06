function jmh_results_plot()

arr_size = [32, 64, 128, 256, 512, 1024, 16384];

passport.name = 'JPassport';
passport.ops = [4980282, 4864624, 4623509, 4055315, 3611582, 2603529, 166257, 93783830];
passport.err = [36781, 48523,69654, 38364, 30586, 82543, 2793, 706989];

jna.name = 'JNA';
jna.ops = [965025, 948315,908491,804422, 704481, 558143,  67235, 1844900];
jna.err = [16695, 15394, 17186, 4370, 17630, 3294, 2339, 128607];

jna_direct.name = 'JNA Direct';
jna_direct.ops = [4910614, 4531601, 3629602, 2760306, 1864532, 1126374, 76438, 12900678];
jna_direct.err = [73626, 35901, 62248, 19049, 23614, 15975, 2716, 331764];

jni.name = 'JNI';
jni.ops = [8752710, 7473582, 5382671, 3671953, 2226667, 1232497, 76333, 91683788];
jni.err = [96588, 93759, 71644, 45954, 61307, 41455, 1708, 1455563];


data = [jna, jna_direct, jni, passport];
do_plot('array_length_32', 'Array length 32', data, 1);
do_plot('array_length_64', 'Array length 64', data, 2);
do_plot('array_length_128', 'Array length 128', data, 3);
do_plot('array_length_256', 'Array length 256', data, 4);
do_plot('array_length_512', 'Array length 512', data, 5);
do_plot('array_length_1024', 'Array length 1024', data, 6);
do_plot('array_length_16384', 'Array length 16384', data, 7);
do_plot('passing_primatives', 'Passing Primatives', data, 8);
end

function do_plot(fname, plot_name, data, idx)
    
    figure(idx);
    clf
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
    saveas(idx, [fname '.png']);
end