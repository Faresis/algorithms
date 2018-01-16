package ua.dp.mign.recursion;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class PickingTeam {
    private static void selectTeams(List<String> group, int teamSize, String team) {
        if (teamSize < 1 || teamSize > group.size())
            return;

        String current = group.get(0);

        if (teamSize == 1)
            System.out.println(team + current);

        List<String> subGroup = group.subList(1, group.size());
        selectTeams(subGroup, teamSize - 1, team + current);
        selectTeams(subGroup, teamSize, team);
    }

    public static void main(String[] args) {
        List<String> group = Stream.of("A", "B", "C", "D", "E").collect(Collectors.toList());
        int teamSize = 3;

        selectTeams(group, teamSize, "");
    }
}
