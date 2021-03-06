﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SpellExporter
{
    public static class SpellExporterService
    {
        public static string Nl = "\n";
        private static string tab = "\t";

        private static string schoolSt = "École : ";
        private static string levelSt = "Niveau : ";
        private static string castingSt = "Temps d’incantation : ";
        private static string componentsSt = "Composantes : ";
        private static string rangeSt = "Portée : ";
        private static string effectSt = "Effet : ";
        private static string targetSt = "Cible : ";
        private static string targetsSt = "Cibles : ";
        private static string areaSt = "Zone d’effet : ";
        private static string durationSt = "Durée : ";
        private static string savingSt = "Jet de sauvegarde : ";
        private static string resistanceSt = "Résistance à la magie : ";
        
        private static string lnSep = " ;";
        private static string scRegSt = " [";
        private static string scRegEnd = "]";
        private static string scBrSt = " (";
        private static string scBrEnd = ")";
        
        private static string spellMagSt = "Ens/Mag ";
        private static string spellPriSt = "Prê ";
        private static string spellPalSt = "Pal ";
        private static string spellBarSt = "Bard ";
        private static string spellDruSt = "Dru ";        
        private static string spellStrSt = "Rôd ";
        
        private static int lastIdx = 0;

        private static string spellSep = "--)";

        public static IList<Spell> ParseList(string toParse)
        {
            IList<Spell> spells = new List<Spell>();

            string[] spellTab0 = toParse.Split(new string[] { spellSep },StringSplitOptions.RemoveEmptyEntries);

            // 0 removed
            for (int i = 1; i < spellTab0.Length; i++)
            {
                string spellStr = spellTab0[i];
                string stringToParse = spellStr;
                int idx = spellStr.LastIndexOf(Nl);
                if (idx != -1)
                {
                    stringToParse = spellStr.Substring(0, idx);
                }

                idx = stringToParse.IndexOf(tab);
                if (idx == 0)
                {
                    stringToParse = stringToParse.Substring(1);
                }

                Spell spell = Parse(stringToParse);
                spells.Add(spell);
            }

            return spells;
        }

        public static Spell Parse(string toParse)
        {
            if (toParse == null)
            {
                throw new ArgumentNullException(toParse);
            }

            Spell spell = new Spell();
            spell.Name = Parse(toParse, String.Empty, Nl);
            ParseSchool(toParse, spell);
            ParseLevel(toParse, spell);
            spell.CastingTime = Parse(toParse, castingSt, Nl);
            spell.Components = Parse(toParse, componentsSt, Nl);
            spell.Range = Parse(toParse, rangeSt, Nl);
            spell.TargetEffectArea = ParseTargetEffectArea(toParse);
            spell.Duration = Parse(toParse, durationSt, Nl);
            spell.SavingThrow = Parse(toParse, savingSt, lnSep);
            spell.SpellResistance = Parse(toParse, resistanceSt, Nl);
            spell.Description = ParseDescription(toParse);
            
            return spell;
        }

        public static void ParseSchool(string toParse, Spell spell)
        {
            string fullSchool = Parse(toParse, schoolSt, lnSep);
            spell.School = ParseSchool(fullSchool);
            spell.Branch = ParseBranch(fullSchool);
            spell.Register = ParseRegister(fullSchool);
        }

        private static string ParseSchool(string fullSchool)
        {            
            string school = fullSchool;

            if (fullSchool.Contains(scRegSt))
            {
                school = Parse(fullSchool, String.Empty, scRegSt);
            }

            if (fullSchool.Contains(scBrSt))
            {
                school = Parse(fullSchool, String.Empty, scBrSt);
            }

            return school;
        }

        private static string ParseRegister(string fullSchool)
        {            
            string register = String.Empty;

            if (fullSchool.Contains(scRegSt))
            {
                register = Parse(fullSchool, scRegSt, scRegEnd);
            }

            return register;
        }

        private static string ParseBranch(string fullSchool)
        {            
            string register = String.Empty;

            if (fullSchool.Contains(scBrSt))
            {
                register = Parse(fullSchool, scBrSt, scBrEnd);
            }

            return register;
        }

        public static void ParseLevel(string toParse, Spell spell)
        {
            string fullLevel = Parse(toParse, levelSt, Nl);
            spell.LevelMagician = Parse(toParse, spellMagSt, 1);
            spell.LevelPriest = Parse(toParse, spellPriSt, 1);
            spell.LevelPaladin = Parse(toParse, spellPalSt, 1);
            spell.LevelBard = Parse(toParse, spellBarSt, 1);
            spell.LevelDruid = Parse(toParse, spellDruSt, 1);
            spell.LevelStriker = Parse(toParse, spellStrSt, 1);
        }

        public static string ParseTargetEffectArea(string toParse)
        {
            string targetEffectArea = String.Empty;
            targetEffectArea = Parse(toParse, effectSt, Nl);
            if (targetEffectArea.Length == 0)
            {
                targetEffectArea = Parse(toParse, targetSt, Nl);
            }

            if (targetEffectArea.Length == 0)
            {
                targetEffectArea = Parse(toParse, targetsSt, Nl);
            }

            if (targetEffectArea.Length == 0)
            {
                targetEffectArea = Parse(toParse, areaSt, Nl);
            }

            return targetEffectArea;
        }

        private static string ParseDescription(string toParse)
        {
            return toParse.Substring(lastIdx + 1);
        }

        private static string Parse(string toParse, string start, string end)
        {
            int idxSt = 0;
            int idxEnd = 0;
            idxSt = toParse.IndexOf(start);            

            string parsed = String.Empty;
            if (idxSt != -1)
            {
                idxEnd = toParse.IndexOf(end, idxSt);
                if (idxEnd == -1)
                {
                    idxEnd = toParse.IndexOf(Nl, idxSt);
                }

                int length = idxEnd - idxSt - start.Length;
                parsed = toParse.Substring(idxSt + start.Length, length);
            }

            if (parsed != String.Empty)
            {
                lastIdx = idxEnd;
            }

            return parsed;
        }

        private static string Parse(string toParse, string start, int length)
        {
            int idxSt = 0;
            int idxEnd = 0;
            idxSt = toParse.IndexOf(start);

            string parsed = String.Empty;
            if (idxSt != -1)
            {                
                parsed = toParse.Substring(idxSt + start.Length, length);
                idxEnd = idxSt + start.Length + length;
            }

            if (parsed != String.Empty)
            {
                lastIdx = idxEnd;
            }

            return parsed;
        }

        public static void ParseShortDescriptionFullListAndMerge(string toParse, IList<Spell> spells)
        {            
            string[] spellTab0 = toParse.Split(new string[] { spellSep }, StringSplitOptions.RemoveEmptyEntries);

            // 0 removed
            for (int i = 1; i < spellTab0.Length; i++)
            {
                string sdList = spellTab0[i];
                string stringToParse = sdList;
                int idx = sdList.LastIndexOf(Nl);
                if (idx != -1)
                {
                    stringToParse = sdList.Substring(0, idx);
                }

                idx = stringToParse.IndexOf(tab);
                if (idx == 0)
                {
                    stringToParse = stringToParse.Substring(1);
                }

                ParseShortDescriptionAndMerge(stringToParse, spells);
            }
        }

        public static void ParseShortDescriptionAndMerge(string toParse, IList<Spell> spells)
        {
            string[] spellList = toParse.Split(new string[] { Nl }, StringSplitOptions.RemoveEmptyEntries);
            foreach (string spellLine in spellList)
            {
                string spellName = spellLine;
                int idxPoint = spellName.IndexOf(".", 0);
                if (idxPoint != -1)
                {
                    spellName = spellName.Substring(0, idxPoint);
                }

                int idx = spellName.IndexOf(" (", 0);
                if (idx != -1)
                {
                    spellName = spellName.Substring(0, idx);
                }

                IEnumerable<Spell> existSpells = from sp in spells where sp.Name == spellName select sp;
                if (existSpells.Count() > 0)
                {
                    Spell spell = existSpells.First();
                    string shortDescription = spellLine.Substring(idxPoint + 2);

                    spell.ShortDescription = shortDescription;
                }
            }
        }
    }
}
